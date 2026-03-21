package dev.brunoan99.app;

import dev.brunoan99.benchmarks.compression.RLEBenchmark;

public class App {
  public static void main(String[] args) throws Exception {
    long byHelperStartTime = System.nanoTime();
    RLEBenchmark.benchmarkRandomTest(false, false);
    long byHelperEndTime = System.nanoTime();
    long byHelperTime = byHelperEndTime - byHelperStartTime;
    IO.println("Timing By Helper: " + byHelperTime);
  }
}
