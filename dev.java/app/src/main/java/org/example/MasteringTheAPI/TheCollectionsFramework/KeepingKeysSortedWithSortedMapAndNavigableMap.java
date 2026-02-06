package org.example.MasteringTheAPI.TheCollectionsFramework;

import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;

import org.example.Utils.SubArticle;

public class KeepingKeysSortedWithSortedMapAndNavigableMap implements SubArticle {
  public void execute() {
    IO.println("Methods Added by NavigableMap");
    NavigableMap<Integer, String> map = new TreeMap<>();
    map.put(1, "one");
    map.put(2, "two");
    map.put(3, "three");
    map.put(4, "four");
    map.put(5, "five");

    map.keySet().forEach(key -> IO.print(key + " "));
    IO.println();

    NavigableSet<Integer> descendingKeys = map.descendingKeySet();
    descendingKeys.forEach(key -> IO.print(key + " "));
    IO.println();
  }
}
