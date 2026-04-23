package dev.brunoan99.benchmarks.compression;

import dev.brunoan99.algorithms.compression.DEFLATE;
import dev.brunoan99.utilities.Accumulator;
import dev.brunoan99.utilities.BenchmarkRunner;
import dev.brunoan99.utilities.RandomInputHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class DEFLATEBenchmark extends BenchmarkRunner<DEFLATEBenchmark.ResultLine, DEFLATEBenchmark.ResultFinal> {
  public DEFLATEBenchmark() {
    super(new GeneralConfig());
  }

  public DEFLATEBenchmark(GeneralConfig config) {
    super(config);
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

  public static class DEFLATEBenchmarkAccumulator
      implements Accumulator<DEFLATEBenchmark.ResultLine, DEFLATEBenchmark.ResultFinal> {
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

  @Override
  protected ResultLine processFunction(RandomInputHelper.InputLine inputLine) {
    String text = inputLine.value();

    long compressingStartTime = System.nanoTime();
    DEFLATE deflate = new DEFLATE();
    String compressed = deflate.compress(text);
    long compressingEndTime = System.nanoTime();
    long compressingTime = compressingEndTime - compressingStartTime;

    long decompressingStartTime = System.nanoTime();
    String decompressed = deflate.decompress(compressed);
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

  @Override
  protected ArrayList<ArrayList<String>> formatFunction(Map<InputParam, ResultFinal> resMap) {
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    table.add(new ArrayList<String>(
        Arrays.asList("String Length", "Max Sequence Length", "Tests Number",
            "Mean Compression Ratio", "Mean Compressing Time (ns)", "Mean Decompressing Time (ns)")));
    resMap.entrySet().stream()
        .sorted(java.util.Comparator
            .comparingInt(
                (java.util.Map.Entry<InputParam, ResultFinal> e) -> e.getKey().randomStringLength())
            .thenComparingInt(e -> e.getKey().maxSequenceLength()))
        .forEach(entry -> {
          InputParam input = entry.getKey();
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

  @Override
  protected Accumulator<ResultLine, ResultFinal> accumulatorFactory() {
    return new DEFLATEBenchmarkAccumulator();
  }

}
