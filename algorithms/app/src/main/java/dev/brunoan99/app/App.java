package dev.brunoan99.app;

import dev.brunoan99.benchmarks.compression.rANSArithmeticHuffmanBenchmark;
import dev.brunoan99.utilities.BenchmarkRunner;

public class App {
  public static void main(String[] args) throws Exception {
    BenchmarkRunner.BenchmarkConfig benchConfig = new BenchmarkRunner.BenchmarkConfig(
        64,
        2_048,
        4,
        32,
        100);
    long timestamp = System.currentTimeMillis();
    String folder = "../benchmarks/benchmarks_results/compression/rans_arithmetic_coding_huffman/";
    String path = folder + "rans_arithmetic_coding_huffman_random_tests_results_" + timestamp + ".txt";
    BenchmarkRunner.GeneralConfig config = new BenchmarkRunner.GeneralConfig(
        benchConfig,
        true,
        true,
        path);

    // long arithmeticCodingStartTime = System.nanoTime();
    // ArithmeticCodingBenchmark.benchmarkRandomTests(config);
    // long arithmeticCodingEndTime = System.nanoTime();
    // long arithmeticCodingTime = arithmeticCodingEndTime -
    // arithmeticCodingStartTime;
    // IO.println("Arithmetic Coding Timing: " + arithmeticCodingTime);

    // long ransStartTime = System.nanoTime();
    // rANSBenchmark.benchmarkRandomTests(config);
    // long ransEndTime = System.nanoTime();
    // long ransTime = ransEndTime - ransStartTime;
    // IO.println("rANS Timing: " + ransTime);

    long multipleStartTime = System.nanoTime();
    rANSArithmeticHuffmanBenchmark.benchmarkRandomTests(config);
    long multipleEndTime = System.nanoTime();
    long multipleTime = multipleEndTime - multipleStartTime;
    IO.println("rANS + Arithmetic Coding + Huffman Timing: " + multipleTime);
  }
}
