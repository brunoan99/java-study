package dev.brunoan99.benchmarks.compression;

import dev.brunoan99.algorithms.compression.Huffman;
import dev.brunoan99.utilities.Accumulator;
import dev.brunoan99.utilities.BenchmarkRunner;
import dev.brunoan99.utilities.RandomInputHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class HuffmanBenchmark {
  private HuffmanBenchmark() {
  }

  record ResultLine(
      int originalSize,
      int compressedSize,
      long initializingTime,
      long encodingTime,
      long decodingTime) {
  }

  record ResultFinal(
      int count,
      float meanOriginalSize,
      float meanCompressedSize,
      float meanCompressingRatio,
      float meanInitializingTime,
      float meanEncodingTime,
      float meanDecodingTime) {
  }

  public static class HuffmanBenchmarkAccumulator
      implements Accumulator<HuffmanBenchmark.ResultLine, HuffmanBenchmark.ResultFinal> {
    int count = 0;
    float sumOriginalSize = 0L;
    float sumCompressedSize = 0L;
    float sumCompressingRatio = 0L;
    float sumInitializingTime = 0L;
    float sumEncodingTime = 0L;
    float sumDecodingTime = 0L;

    @Override
    public void add(ResultLine v) {
      count++;
      sumOriginalSize += v.originalSize;
      sumCompressedSize += v.compressedSize;
      sumCompressingRatio += ((float) v.compressedSize / (float) v.originalSize);
      sumInitializingTime += v.initializingTime;
      sumEncodingTime += v.encodingTime;
      sumDecodingTime += v.decodingTime;
    }

    @Override
    public ResultFinal result() {
      return new ResultFinal(
          count,
          sumOriginalSize / count,
          sumCompressedSize / count,
          sumCompressingRatio / count,
          sumInitializingTime / count,
          sumEncodingTime / count,
          sumDecodingTime / count);
    }
  }

  private static ResultLine processFunction(RandomInputHelper.InputLine inputLine) {
    try {

      String text = inputLine.value();

      long initializingStartTime = System.nanoTime();
      Huffman huffman = new Huffman(text);
      long initializingEndTime = System.nanoTime();
      long initializingTime = initializingEndTime - initializingStartTime;

      long encodingStartTime = System.nanoTime();
      String encoded = huffman.encode(text);
      long encodingEndTime = System.nanoTime();
      long encodingTime = encodingEndTime - encodingStartTime;

      long decodingStartTime = System.nanoTime();
      String decoded = huffman.decode(encoded);
      long decodingEndTime = System.nanoTime();
      long decodingTime = decodingEndTime - decodingStartTime;

      if (!text.equals(decoded)) {
        IO.println("------------------------------");
        IO.println("Original: " + text);
        IO.println("Decoded: " + decoded);
        IO.println("------------------------------");
        throw new RuntimeException("Decoded string does not match original");
      }
      return new ResultLine(
          text.length(),
          encoded.length(),
          initializingTime,
          encodingTime,
          decodingTime);
    } catch (IllegalArgumentException e) {
      IO.println("------------------------------");
      IO.println("Original: " + inputLine.value());
      IO.println("------------------------------");
      throw e;
    }
  }

  private static ArrayList<ArrayList<String>> formatFunction(Map<BenchmarkRunner.InputParam, ResultFinal> resMap) {
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    table.add(new ArrayList<String>(
        Arrays.asList(
            "String Length",
            "Max Sequence Length",
            "Tests Number",
            "Mean Compression Ratio",
            "Mean Initializing Time (ns)",
            "Mean Encoding Time (ns)",
            "Mean Decoding Time (ns)")));
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
                  String.format("%,.0f", resfinal.meanEncodingTime),
                  String.format("%,.0f", resfinal.meanDecodingTime))));
        });
    return table;
  }

  public static void benchmarkRandomTests(boolean logOnConsole, boolean saveFile) throws Exception {
    BenchmarkRunner.BenchmarkConfig benchConfig = new BenchmarkRunner.BenchmarkConfig(
        32,
        512,
        1,
        32,
        1000);

    long timestamp = System.currentTimeMillis();
    String folder = "../benchmarks/benchmarks_results/compression/huffman/";
    String path = folder + "huffman_compressor_random_tests_results_" + timestamp + ".txt";

    BenchmarkRunner.GeneralConfig config = new BenchmarkRunner.GeneralConfig(
        benchConfig,
        logOnConsole,
        saveFile,
        path);

    BenchmarkRunner benchRunner = new BenchmarkRunner(config);

    Supplier<Accumulator<ResultLine, ResultFinal>> accumulatorFactory = HuffmanBenchmarkAccumulator::new;
    Function<RandomInputHelper.InputLine, ResultLine> processFunction = HuffmanBenchmark::processFunction;
    Function<Map<BenchmarkRunner.InputParam, ResultFinal>, ArrayList<ArrayList<String>>> formatFunction = HuffmanBenchmark::formatFunction;

    benchRunner.benchmarkRandomTest(
        accumulatorFactory,
        processFunction,
        formatFunction);
  }
}
