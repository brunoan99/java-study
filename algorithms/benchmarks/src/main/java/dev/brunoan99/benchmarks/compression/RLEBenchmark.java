package dev.brunoan99.benchmarks.compression;

import dev.brunoan99.algorithms.compression.RLE;

import dev.brunoan99.utilities.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RLEBenchmark {

  private RLEBenchmark() {
  }

  private record RandomTestConfig(int randomTestsNumber, int randomStringLength, int maxSequenceLength) {
  }

  private record ProcessingResult(int inputSize, int compressedSize, double compressionRatio, long compressingTime,
      long decompressingTime) {
  }

  private record RandomTestResults(RandomTestConfig config, double meanCompressionRatio, double meanCompressingTime,
      double meanDecompressingTime) {
  }

  private static RandomTestResults generateAndProcess(RandomTestConfig config) {
    String[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");
    List<ProcessingResult> results = new ArrayList<>();
    for (int i = 0; i < config.randomTestsNumber(); i++) {
      StringBuilder sb = new StringBuilder();
      while (sb.length() < config.randomStringLength()) {
        int remaining = config.randomStringLength() - sb.length();
        int maxSeq = Math.min(config.maxSequenceLength(), remaining);
        int seqLen = (int) (Math.random() * maxSeq) + 1;
        sb.append(characters[(int) (Math.random() * characters.length)].repeat(seqLen));
      }
      String input = sb.toString();

      long compressingStartTime = System.nanoTime();
      String compressed = RLE.compress(input);
      long compressingEndTime = System.nanoTime();
      long compressingTime = compressingEndTime - compressingStartTime;

      long decompressingStartTime = System.nanoTime();
      String decompressed = RLE.decompress(compressed);
      long decompressingEndTime = System.nanoTime();
      long decompressingTime = decompressingEndTime - decompressingStartTime;

      double compressedRatio = (double) compressed.length() / input.length();

      if (!input.equals(decompressed)) {
        throw new RuntimeException("Decompressed string does not match original");
      }
      results.add(new ProcessingResult(input.length(), compressed.length(), compressedRatio, compressingTime,
          decompressingTime));
    }
    double meanCompressionRatio = results.stream()
        .mapToDouble(ProcessingResult::compressionRatio)
        .average()
        .orElse(0.0);
    double meanCompressingTime = results.stream()
        .mapToLong(ProcessingResult::compressingTime)
        .average()
        .orElse(0.0);
    double meanDecompressingTime = results.stream()
        .mapToLong(ProcessingResult::decompressingTime)
        .average()
        .orElse(0.0);
    return new RandomTestResults(config, meanCompressionRatio, meanCompressingTime, meanDecompressingTime);
  }

  private static ArrayList<String> process(RandomTestConfig config) {
    RandomTestResults result = generateAndProcess(config);
    return new ArrayList<String>(Arrays.asList(
        String.valueOf(config.randomStringLength()),
        String.valueOf(config.maxSequenceLength()),
        String.valueOf(config.randomTestsNumber()),
        String.format("%.15f", result.meanCompressionRatio()),
        String.format("%.0f", result.meanCompressingTime()),
        String.format("%.0f", result.meanDecompressingTime())));
  }

  public static void benchmarkRandomTests() throws Exception {
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    table.add(new ArrayList<String>(
        Arrays.asList("String Length", "Max Sequence Length", "Tests Number",
            "Mean Compression Ratio", "Mean Compressing Time (ns)", "Mean Decompressing Time (ns)")));

    int randomTestsNumber = 1_000;

    int minRandomStringLength = 64; // 64B
    int maxRandomStringLength = 67_108_864; // 64MB

    int minMaxSequenceLength = 4; // 4B
    int maxMaxSequenceLength = 65_536; // 64KB

    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    List<Callable<ArrayList<String>>> tasks = new ArrayList<>();

    for (int randomStringLength = minRandomStringLength; randomStringLength <= maxRandomStringLength; randomStringLength *= 2) {
      for (int maxSequenceLength = minMaxSequenceLength; maxSequenceLength <= maxMaxSequenceLength; maxSequenceLength *= 2) {
        if (maxSequenceLength < randomStringLength / 262_144) {
          continue;
        }
        if (maxSequenceLength > randomStringLength / 4) {
          break;
        }
        final RandomTestConfig config = new RandomTestConfig(randomTestsNumber, randomStringLength, maxSequenceLength);
        tasks.add(() -> process(config));
      }
    }

    for (Future<ArrayList<String>> future : pool.invokeAll(tasks)) {
      table.add(future.get());
    }
    pool.shutdown();

    String formattedTable = Table.formatTable(table);
    System.out.println(formattedTable);
    try {
      long timestamp = System.currentTimeMillis();
      String folder = "../benchmarks/rle/benchmarks_results";
      String filename = folder + "/rle_compressor_random_tests_results_" + timestamp + ".txt";
      FileWriter writer = new FileWriter(filename);
      writer.write(formattedTable);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
