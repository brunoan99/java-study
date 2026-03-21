package dev.brunoan99.app;

import dev.brunoan99.benchmarks.compression.DEFLATEBenchmark;
import dev.brunoan99.benchmarks.compression.LZ77Benchmark;
import dev.brunoan99.benchmarks.compression.HuffmanBenchmark;
import dev.brunoan99.benchmarks.compression.RLEBenchmark;

public class App {
  public static void main(String[] args) throws Exception {
    long byHelperStartTime = System.nanoTime();
    DEFLATEBenchmark.benchmarkRandomTests(true, true);
    long byHelperEndTime = System.nanoTime();
    long byHelperTime = byHelperEndTime - byHelperStartTime;
    IO.println("Timing By Helper: " + byHelperTime);
  }
}
