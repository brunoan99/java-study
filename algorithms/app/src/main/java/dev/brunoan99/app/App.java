package dev.brunoan99.app;

import dev.brunoan99.benchmarks.compression.ArithmeticCodingBenchmark;

public class App {
  public static void main(String[] args) throws Exception {
    long byHelperStartTime = System.nanoTime();
    ArithmeticCodingBenchmark.benchmarkRandomTests(true, true);
    long byHelperEndTime = System.nanoTime();
    long byHelperTime = byHelperEndTime - byHelperStartTime;
    IO.println("Timing By Helper: " + byHelperTime);
  }
}
