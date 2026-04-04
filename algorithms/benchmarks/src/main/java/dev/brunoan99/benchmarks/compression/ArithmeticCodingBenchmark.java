package dev.brunoan99.benchmarks.compression;

import dev.brunoan99.algorithms.compression.ArithmeticCoding;
import dev.brunoan99.utilities.Accumulator;
import dev.brunoan99.utilities.BenchmarkRunner;
import dev.brunoan99.utilities.RandomInputHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArithmeticCodingBenchmark {
  private ArithmeticCodingBenchmark() {
  }

  record ResultLine(
      int originalSize,
      int compressedSize,
      long initializingTime,
      long compressingTime,
      long decompressingTime) {
  }

  record ResultFinal(
      int count,
      float meanOriginalSize,
      float meanCompressedSize,
      float meanCompressingRatio,
      float meanInitializingTime,
      float meanCompressingTime,
      float meanDecompressingTime) {
  }

  public static class ArithmeticCodingBenchmarkAccumulator
      implements Accumulator<ArithmeticCodingBenchmark.ResultLine, ArithmeticCodingBenchmark.ResultFinal> {
    int count = 0;
    float sumOriginalSize = 0L;
    float sumCompressedSize = 0L;
    float sumCompressingRatio = 0L;
    float sumInitializingTime = 0L;
    float sumCompressingTime = 0L;
    float sumDecompressingTime = 0L;

    @Override
    public void add(ResultLine v) {
      count++;
      sumOriginalSize += v.originalSize;
      sumCompressedSize += v.compressedSize;
      sumCompressingRatio += ((float) v.compressedSize / (float) v.originalSize);
      sumInitializingTime += v.initializingTime;
      sumCompressingTime += v.compressingTime;
      sumDecompressingTime += v.decompressingTime;
    }

    @Override
    public ResultFinal result() {
      return new ResultFinal(
          count,
          sumOriginalSize / count,
          sumCompressedSize / count,
          sumCompressingRatio / count,
          sumInitializingTime / count,
          sumCompressingTime / count,
          sumDecompressingTime / count);
    }
  }

  private static ResultLine processFunction(RandomInputHelper.InputLine inputLine) {
    String text = inputLine.value();

    long initializingStartTime = System.nanoTime();
    ArithmeticCoding ac = new ArithmeticCoding(text);
    long initializingEndTime = System.nanoTime();
    long initializingTime = initializingEndTime - initializingStartTime;

    long compressingStartTime = System.nanoTime();
    BigDecimal compressed = ac.compress(text);
    long compressingEndTime = System.nanoTime();
    long compressingTime = compressingEndTime - compressingStartTime;

    long decompressingStartTime = System.nanoTime();
    String decompressed = ac.decompress(compressed);
    long decompressingEndTime = System.nanoTime();
    long decompressingTime = decompressingEndTime - decompressingStartTime;

    if (!text.equals(decompressed)) {
      throw new RuntimeException("Decompressed string does not match original");
    }

    return new ResultLine(
        text.length(),
        compressed.unscaledValue().bitCount() / 8, // Approximation of compressed size in bytes
        initializingTime,
        compressingTime,
        decompressingTime);
  }

  private static ArrayList<ArrayList<String>> formatFunction(Map<BenchmarkRunner.InputParam, ResultFinal> resMap) {
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    table.add(new ArrayList<String>(
        Arrays.asList("String Length", "Max Sequence Length", "Tests Number",
            "Mean Compression Ratio", "Mean Initializing Time (ns)", "Mean Compressing Time (ns)",
            "Mean Decompressing Time (ns)")));

    resMap.entrySet().stream()
        .sorted(java.util.Comparator
            .comparingInt(
                (java.util.Map.Entry<BenchmarkRunner.InputParam, ResultFinal> e) -> e.getKey().randomStringLength())
            .thenComparingInt(e -> e.getKey().maxSequenceLength()))
        .forEach(entry -> {
          BenchmarkRunner.InputParam input = entry.getKey();
          ResultFinal resfinal = entry.getValue();
          table.add(new ArrayList<String>(
              Arrays.asList(
                  String.valueOf(input.randomStringLength()),
                  String.valueOf(input.maxSequenceLength()),
                  String.valueOf(resfinal.count),
                  String.format("%.10f", resfinal.meanCompressingRatio),
                  String.format("%,.0f", resfinal.meanInitializingTime),
                  String.format("%,.0f", resfinal.meanCompressingTime),
                  String.format("%,.0f", resfinal.meanDecompressingTime))));
        });
    return table;
  }

  public static void benchmarkRandomTests(boolean logOnConsole, boolean saveFile)
      throws Exception {
    BenchmarkRunner.BenchmarkConfig benchConfig = new BenchmarkRunner.BenchmarkConfig(
        64,
        2_048,
        1,
        32,
        1);

    long timestamp = System.currentTimeMillis();
    String folder = "../benchmarks/benchmarks_results/compression/arithmetic_coding/";
    String path = folder + "arithmetic_coding_compressor_random_tests_results_" + timestamp + ".txt";

    BenchmarkRunner.GeneralConfig config = new BenchmarkRunner.GeneralConfig(
        benchConfig,
        logOnConsole,
        saveFile,
        path);
    BenchmarkRunner benchRunner = new BenchmarkRunner(config);

    Supplier<Accumulator<ArithmeticCodingBenchmark.ResultLine, ArithmeticCodingBenchmark.ResultFinal>> accumulatorFactory = ArithmeticCodingBenchmarkAccumulator::new;
    Function<RandomInputHelper.InputLine, ArithmeticCodingBenchmark.ResultLine> processFunction = ArithmeticCodingBenchmark::processFunction;
    Function<Map<BenchmarkRunner.InputParam, ArithmeticCodingBenchmark.ResultFinal>, ArrayList<ArrayList<String>>> formatFunction = ArithmeticCodingBenchmark::formatFunction;

    benchRunner.benchmarkRandomTest(
        accumulatorFactory,
        processFunction,
        formatFunction);
  }
}
