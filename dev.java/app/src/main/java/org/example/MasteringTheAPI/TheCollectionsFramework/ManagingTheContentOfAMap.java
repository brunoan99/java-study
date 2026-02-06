package org.example.MasteringTheAPI.TheCollectionsFramework;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.example.Utils.SubArticle;

public class ManagingTheContentOfAMap implements SubArticle {
  public void execute() {
    Map<String, Integer> map = new HashMap<>();

    map.put("one", 1);
    map.put("two", null);
    map.put("three", 3);
    map.put("four", null);
    map.put("five", 5);

    for (String key : map.keySet()) {
      map.putIfAbsent(key, -1);
    }
    for (int value : map.values()) {
      IO.println("value = " + value);
    }

    Map<Integer, String> map1 = new HashMap<>();

    map1.put(1, "one");
    map1.put(2, "two");
    map1.put(3, "three");

    // List<String> values = new ArrayList<>();
    // for (int key = 0; key < 5; key++) {
    // values.add(map1.getOrDefault(key, "UNDEFINED"));
    // }

    List<String> values = IntStream.range(0, 5)
        .mapToObj(key -> map1.getOrDefault(key, "UNDEFINED"))
        .collect(Collectors.toList());

    IO.println("values = " + values);

    Map<Integer, String> map2 = new HashMap<>();

    map2.put(1, "one");
    map2.put(2, "two");
    map2.put(3, "three");
    map2.put(4, "four");
    map2.put(5, "five");
    map2.put(6, "six");

    Set<Integer> keys = map2.keySet();
    IO.println("keys = " + keys);

    Collection<String> values1 = map2.values();
    IO.println("values = " + values1);

    Set<Map.Entry<Integer, String>> entries = map2.entrySet();
    IO.println("entries = " + entries);

    Map<Integer, String> map3 = Map.ofEntries(
        Map.entry(1, "one"),
        Map.entry(2, "two"),
        Map.entry(3, "three"),
        Map.entry(4, "three"));
    IO.println("map before = " + map3);
    map3 = new HashMap<>(map3);
    map3.values().remove("three");
    IO.println("map after = " + map3);
  }
}
