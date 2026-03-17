package dev.brunoan99.app;

import dev.brunoan99.benchmarks.compression.RLEBenchmark;

public class App {
  public static void main(String[] args) throws Exception {
    RLEBenchmark.benchmarkRandomTests();
  }
}
