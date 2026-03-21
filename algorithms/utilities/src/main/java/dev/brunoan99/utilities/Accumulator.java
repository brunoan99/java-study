package dev.brunoan99.utilities;

public interface Accumulator<V, R> {
  void add(V value);

  R result();
}
