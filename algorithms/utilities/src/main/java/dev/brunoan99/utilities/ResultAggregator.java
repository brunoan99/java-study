package dev.brunoan99.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResultAggregator<K, V> {

  private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();

  public ResultAggregator() {
  }

  public synchronized void add(K key, V value) {
    map.computeIfAbsent(key, k -> value);
  }

  public Map<K, V> getMap() {
    return map;
  }
}
