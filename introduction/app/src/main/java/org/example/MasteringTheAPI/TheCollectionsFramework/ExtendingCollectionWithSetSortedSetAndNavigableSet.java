package org.example.MasteringTheAPI.TheCollectionsFramework;

import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.example.Utils.SubArticle;

public class ExtendingCollectionWithSetSortedSetAndNavigableSet implements SubArticle {
  public void execute() {
    IO.println("\n=== Extending With Set ===");
    List<String> strings = List.of("one", "two", "three", "four", "five", "six");
    Set<String> set = new HashSet<>();
    set.addAll(strings);
    set.forEach(IO::println);

    IO.println("\n=== Extending Set With SortedSet ===");
    SortedSet<String> strings1 = new TreeSet<>(Set.of("a", "b", "c", "d", "e", "f"));
    SortedSet<String> subSet = strings1.subSet("aa", "d");
    IO.println("sub set = " + subSet);

    IO.println("\n=== Extending SortedSet With NavigableSet ===");
    NavigableSet<String> sortedStrings = new TreeSet<>(Set.of("a", "b", "c", "d", "e", "f"));
    IO.println("sorted strings = " + sortedStrings);
    NavigableSet<String> reversedStrings = sortedStrings.descendingSet();
    IO.println("reversed strings = " + reversedStrings);
  }
}
