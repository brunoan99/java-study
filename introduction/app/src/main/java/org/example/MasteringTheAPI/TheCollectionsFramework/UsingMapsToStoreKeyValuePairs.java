package org.example.MasteringTheAPI.TheCollectionsFramework;

import java.util.HashMap;
import java.util.Map;

import org.example.Utils.SubArticle;

public class UsingMapsToStoreKeyValuePairs implements SubArticle {
  public void execute() {
    Map<Integer, String> map = Map.of(
        1, "one",
        2, "two",
        3, "three");
    IO.println("Map0: " + map + ", Class: " + map.getClass());

    Map.Entry<Integer, String> e1 = Map.entry(1, "one");
    Map.Entry<Integer, String> e2 = Map.entry(2, "two");
    Map.Entry<Integer, String> e3 = Map.entry(3, "three");

    Map<Integer, String> map1 = Map.ofEntries(e1, e2, e3);
    IO.println("Map1: " + map1 + ", Class: " + map1.getClass());

    Map<Integer, String> map2 = Map.ofEntries(
        Map.entry(1, "one"),
        Map.entry(2, "two"),
        Map.entry(3, "three"));
    IO.println("Map2: " + map2 + ", Class: " + map2.getClass());

    // Create and populate a map
    Map<String, Integer> ages = new HashMap<>();
    ages.put("Alice", 25);
    ages.put("Bob", 30);
    ages.put("Carol", 28);
    ages.put("David", 35);
    IO.println("Original map: " + ages);

    // Get values by key
    IO.println("");
    IO.println("=== Getting Values ===");
    IO.println("Alice's age: " + ages.get("Alice"));
    IO.println("Eve's age: " + ages.get("Eve")); // Returns null
    IO.println("Eve's age (with default): " + ages.getOrDefault("Eve", 0));

    // Check if key/value exists
    IO.println("");
    IO.println("=== Checking Existence ===");
    IO.println("Contains Bob? " + ages.containsKey("Bob"));
    IO.println("Contains age 28? " + ages.containsValue(28));
    IO.println("Map size: " + ages.size());

    // Update values
    ages.put("Alice", 26); // Updates existing key
    ages.putIfAbsent("Eve", 22); // Only adds if key doesn't exist
    IO.println("");
    IO.println("=== After Updates ===");
    IO.println("Updated map: " + ages);

    // Remove entries
    ages.remove("David");
    IO.println("After removing David: " + ages);

    // Iterate over keys, values, and entries
    IO.println("");
    IO.println("=== Iteration ===");
    IO.println("Keys: " + ages.keySet());
    IO.println("Values: " + ages.values());

    IO.println("");
    IO.println("Entries:");
    for (Map.Entry<String, Integer> entry : ages.entrySet()) {
      IO.println(entry.getKey() + " -> " + entry.getValue());
    }
  }
}

class MapHierarchy {
  /* @formatter:off */
  // Map hierarchy
  //          Map
  //           |
  //       SortedMap
  //           |
  //      NavigableMap
  /* @formatter:on */
}
