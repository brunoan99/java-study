package dev.brunoan99.benchmarks.compression;

import dev.brunoan99.algorithms.compression.RLE;
import dev.brunoan99.utilities.Accumulator;
import dev.brunoan99.utilities.BenchmarkRunner;
import dev.brunoan99.utilities.RandomInputHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class RLEBenchmark {

  private RLEBenchmark() {
  }

  record ResultLine(
      int originalSize,
      int compressedSize,
      long compressingTime,
      long decompressingTime) {
  }

  record ResultFinal(
      int count,
      float meanOriginalSize,
      float meanCompressedSize,
      float meanCompressingRatio,
      float meanCompressingTime,
      float meanDecompressingTime) {
  }

  public static class RLEBenchmarkAccumulator
      implements Accumulator<RLEBenchmark.ResultLine, RLEBenchmark.ResultFinal> {
    int count = 0;
    long sumOriginalSize = 0L;
    long sumCompressedSize = 0L;
    float sumCompressingRatio = 0L;
    long sumCompressingTime = 0L;
    long sumDecompressingTime = 0L;

    @Override
    public void add(ResultLine v) {
      count++;
      sumOriginalSize += v.originalSize;
      sumCompressedSize += v.compressedSize;
      sumCompressingRatio += ((float) v.compressedSize / (float) v.originalSize);
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
          sumCompressingTime / count,
          sumDecompressingTime / count);
    }
  }

  private static ResultLine processFunction(RandomInputHelper.InputLine inputLine) {
    String text = inputLine.value();

    long compressingStartTime = System.nanoTime();
    String compressed = RLE.compress(text);
    long compressingEndTime = System.nanoTime();
    long compressingTime = compressingEndTime - compressingStartTime;

    long decompressingStartTime = System.nanoTime();
    String decompressed = RLE.decompress(compressed);
    long decompressingEndTime = System.nanoTime();
    long decompressingTime = decompressingEndTime - decompressingStartTime;

    if (!text.equals(decompressed)) {
      throw new RuntimeException("Decompressed string does not match original");
    }

    return new ResultLine(
        text.length(),
        compressed.length(),
        compressingTime,
        decompressingTime);
  }

  private static ArrayList<ArrayList<String>> formatFunction(Map<BenchmarkRunner.InputParam, ResultFinal> resMap) {
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    table.add(new ArrayList<String>(
        Arrays.asList("String Length", "Max Sequence Length", "Tests Number",
            "Mean Compression Ratio", "Mean Compressing Time (ns)", "Mean Decompressing Time (ns)")));
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
                  String.format("%,.0f", resfinal.meanCompressingTime),
                  String.format("%,.0f", resfinal.meanDecompressingTime))));
        });
    return table;
  }

  public static void benchmarkRandomTests(BenchmarkRunner.GeneralConfig config)
      throws Exception {
    BenchmarkRunner benchRunner = new BenchmarkRunner(config);

    Supplier<Accumulator<ResultLine, ResultFinal>> accumulatorFactory = RLEBenchmarkAccumulator::new;
    Function<RandomInputHelper.InputLine, ResultLine> processFunction = RLEBenchmark::processFunction;
    Function<Map<BenchmarkRunner.InputParam, ResultFinal>, ArrayList<ArrayList<String>>> formatFunction = RLEBenchmark::formatFunction;

    benchRunner.benchmarkRandomTest(
        accumulatorFactory,
        processFunction,
        formatFunction);
  }
}
