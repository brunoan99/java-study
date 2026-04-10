package dev.brunoan99.utilities;

import dev.brunoan99.utilities.RandomInputHelper.InputLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;

public class BenchmarkRunner {
  private final static int DEFAULT_MIN_RANDOM_STRING_LENGTH = 64;
  private final static int DEFAULT_MAX_RANDOM_STRING_LENGTH = 2_097_152;
  private final static int DEFAULT_MIN_MAX_SEQUENCE_LENGTH = 1;
  private final static int DEFAULT_MAX_MAX_SEQUENCE_LENGTH = 32;
  private final static int DEFAULT_RANDOM_TESTS_NUMBER = 1_000;
  private final static boolean DEFAULT_LOG_ON_CONSOLE = false;
  private final static boolean DEFAULT_SAVE_FILE = false;
  private final static String DEFAULT_FILE_PATH = "";

  public record BenchmarkConfig(
      int minRandomStringLength,
      int maxRandomStringLength,
      int minMaxSequenceLength,
      int maxMaxSequenceLength,
      int testNumber) {
    public BenchmarkConfig() {
      this(
          DEFAULT_MIN_RANDOM_STRING_LENGTH,
          DEFAULT_MAX_RANDOM_STRING_LENGTH,
          DEFAULT_MIN_MAX_SEQUENCE_LENGTH,
          DEFAULT_MAX_MAX_SEQUENCE_LENGTH,
          DEFAULT_RANDOM_TESTS_NUMBER);
    }
  }

  public record GeneralConfig(
      BenchmarkConfig benchConfig,
      boolean logOnConsole,
      boolean saveFile,
      String pathToSave) {
    public GeneralConfig() {
      this(
          new BenchmarkConfig(),
          DEFAULT_LOG_ON_CONSOLE,
          DEFAULT_SAVE_FILE,
          DEFAULT_FILE_PATH);
    }
  }

  final GeneralConfig config;

  public BenchmarkRunner(GeneralConfig config) {
    this.config = config;
  }

  public BenchmarkRunner(BenchmarkConfig config) {
    this.config = new GeneralConfig(
        config,
        DEFAULT_LOG_ON_CONSOLE,
        DEFAULT_SAVE_FILE,
        DEFAULT_FILE_PATH);
  }

  public BenchmarkRunner() {
    this.config = new GeneralConfig();
  }

  public record InputParam(
      int randomStringLength,
      int maxSequenceLength) {
  }

  private void logAndSave(
      ArrayList<ArrayList<String>> table) throws Exception {
    String formattedTable = Table.formatTable(table);
    if (config.logOnConsole)
      System.out.println(formattedTable);
    if (config.saveFile)
      FileHelper.write(config.pathToSave, formattedTable);
  }

  public <TestResult, BenchmarkResult> void benchmarkRandomTest(
      Supplier<Accumulator<TestResult, BenchmarkResult>> accumulatorFactory,
      Function<InputLine, TestResult> processFunction,
      Function<Map<InputParam, BenchmarkResult>, ArrayList<ArrayList<String>>> formatFunction) throws Exception {
    ConcurrentHashMap<InputParam, BenchmarkResult> resultAggregator = new ConcurrentHashMap<>();
    ArrayList<InputParam> allParams = new ArrayList<>();

    for (int rsl = config.benchConfig.maxRandomStringLength(); rsl >= config.benchConfig
        .minRandomStringLength(); rsl /= 2) {
      for (int msl = config.benchConfig.minMaxSequenceLength(); msl <= config.benchConfig
          .maxMaxSequenceLength(); msl *= 2) {
        allParams.add(new InputParam(rsl, msl));
      }
    }

    ExecutorService executor = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors());

    List<Future<?>> futures = new ArrayList<>();

    for (InputParam inputParam : allParams) {
      futures.add(executor.submit(() -> {
        Random localRandom = new Random();
        Accumulator<TestResult, BenchmarkResult> benchmarkAccumulator = accumulatorFactory.get();
        for (int i = 0; i < config.benchConfig.testNumber(); i++) {
          RandomInputHelper.InputLine input = RandomInputHelper.generateLine(
              inputParam.randomStringLength(),
              inputParam.maxSequenceLength(),
              localRandom);
          TestResult testResult = processFunction.apply(input);
          benchmarkAccumulator.add(testResult);
        }
        BenchmarkResult benchmarkResult = benchmarkAccumulator.result();
        resultAggregator.putIfAbsent(inputParam, benchmarkResult);
      }));
    }
    for (Future<?> f : futures) {
      f.get();
    }
    executor.shutdown();

    ArrayList<ArrayList<String>> table = formatFunction.apply(resultAggregator);
    logAndSave(table);
  }

}
