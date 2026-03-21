package dev.brunoan99.utilities;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class ResultAggregator<K, V, R> {
  public interface Accumulator<V, R> {
    void add(V value);

    R result();
  }

  private final ConcurrentHashMap<K, Accumulator<V, R>> map = new ConcurrentHashMap<>();
  private final Supplier<Accumulator<V, R>> factory;

  public ResultAggregator(Supplier<Accumulator<V, R>> factory) {
    this.factory = factory;
  }

  public synchronized void add(K key, V value) {
    map.computeIfAbsent(key, k -> factory.get()).add(value);
  }

  public HashMap<K, R> compute() {
    HashMap<K, R> result = new HashMap<>();
    for (K key : map.keySet()) {
      result.put(key, map.get(key).result());
    }
    return result;
  }
}
