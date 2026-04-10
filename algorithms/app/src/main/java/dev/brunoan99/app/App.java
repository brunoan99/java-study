package dev.brunoan99.app;

import dev.brunoan99.benchmarks.compression.ArithmeticCodingBenchmark;
import dev.brunoan99.benchmarks.compression.rANSBenchmark;

public class App {
  public static void main(String[] args) throws Exception {
    long arithmeticCodingStartTime = System.nanoTime();
    ArithmeticCodingBenchmark.benchmarkRandomTests(true, false);
    long arithmeticCodingEndTime = System.nanoTime();
    long arithmeticCodingTime = arithmeticCodingEndTime -
        arithmeticCodingStartTime;
    IO.println("Arithmetic Coding Timing: " + arithmeticCodingTime);

    long ransStartTime = System.nanoTime();
    rANSBenchmark.benchmarkRandomTests(true, false);
    long ransEndTime = System.nanoTime();
    long ransTime = ransEndTime - ransStartTime;
    IO.println("rANS Timing: " + ransTime);
  }
}
