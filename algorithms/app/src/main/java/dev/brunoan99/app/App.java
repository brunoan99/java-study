package dev.brunoan99.app;

import dev.brunoan99.benchmarks.compression.HuffmanBenchmark;

public class App {
  public static void main(String[] args) throws Exception {
    HuffmanBenchmark.benchmarkRandomTests();
  }
}
