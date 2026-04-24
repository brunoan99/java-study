package dev.brunoan99.app;

import dev.brunoan99.benchmarks.compression.rANSArithmeticHuffmanBenchmark;
import dev.brunoan99.utilities.BenchmarkCharsetRunner;

public class App {
  public static void main(String[] args) throws Exception {
    BenchmarkCharsetRunner.BenchmarkConfig benchConfig = new BenchmarkCharsetRunner.BenchmarkConfig(
        64,
        2_048,
        4,
        32,
        2,
        52,
        100);
    long timestamp = System.currentTimeMillis();
    String folder = "../benchmarks/benchmarks_results/compression/rans_arithmetic_coding_huffman/";
    String path = folder + "rans_arithmetic_coding_huffman_random_tests_results_" + timestamp + ".txt";
    BenchmarkCharsetRunner.GeneralConfig config = new BenchmarkCharsetRunner.GeneralConfig(
        benchConfig,
        true,
        true,
        path);

    long multipleStartTime = System.nanoTime();
    rANSArithmeticHuffmanBenchmark ransArithmeticHuffmanBenchmark = new rANSArithmeticHuffmanBenchmark(config);
    ransArithmeticHuffmanBenchmark.benchmarkRandomTest();

    long multipleEndTime = System.nanoTime();
    long multipleTime = multipleEndTime - multipleStartTime;
    IO.println("rANS + Arithmetic Coding + Huffman Timing: " + multipleTime);
  }
}
