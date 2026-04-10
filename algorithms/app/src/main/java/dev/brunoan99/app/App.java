package dev.brunoan99.app;

import dev.brunoan99.benchmarks.compression.ArithmeticCodingBenchmark;
import dev.brunoan99.benchmarks.compression.rANSBenchmark;
import dev.brunoan99.utilities.BenchmarkRunner;

public class App {
  public static void main(String[] args) throws Exception {
    BenchmarkRunner.BenchmarkConfig benchConfig = new BenchmarkRunner.BenchmarkConfig(
        64,
        512,
        1,
        32,
        1000);
    BenchmarkRunner.GeneralConfig config = new BenchmarkRunner.GeneralConfig(
        benchConfig,
        true,
        false,
        null);

    long arithmeticCodingStartTime = System.nanoTime();
    ArithmeticCodingBenchmark.benchmarkRandomTests(config);
    long arithmeticCodingEndTime = System.nanoTime();
    long arithmeticCodingTime = arithmeticCodingEndTime -
        arithmeticCodingStartTime;
    IO.println("Arithmetic Coding Timing: " + arithmeticCodingTime);

    long ransStartTime = System.nanoTime();
    rANSBenchmark.benchmarkRandomTests(config);
    long ransEndTime = System.nanoTime();
    long ransTime = ransEndTime - ransStartTime;
    IO.println("rANS Timing: " + ransTime);
  }
}
