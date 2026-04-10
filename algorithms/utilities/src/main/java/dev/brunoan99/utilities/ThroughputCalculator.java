package dev.brunoan99.utilities;

public class ThroughputCalculator {
  private static final String[] UNITS = { "B/s", "KB/s", "MB/s", "GB/s", "TB/s", "PB/s" };

  private final Number bits;
  private final Number nanoseconds;

  public ThroughputCalculator(Number bits, Number nanoseconds) {
    this.bits = bits;
    this.nanoseconds = nanoseconds;
  }

  public String format() {
    double bytesPerSecond = (bits.longValue() / 8) / (nanoseconds.longValue() / 1_000_000_000.0);

    int unitIndex = 0;
    double value = bytesPerSecond;

    while (value > 1024.0 && unitIndex < UNITS.length - 1) {
      value /= 1024.0;
      unitIndex++;
    }

    return String.format("%,.2f %s", value, UNITS[unitIndex]);
  }

  public double getBitsPerSecond() {
    return (bits.longValue() / 8.0) / (nanoseconds.longValue() / 1_000_000_000.0);
  }

  @Override
  public String toString() {
    return format();
  }
}
