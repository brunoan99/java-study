package org.example.MasteringTheAPI.TheCollectionsFramework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.example.Utils.SubArticle;

public class CreatingAndProcessingDataWithTheCollectionsFactoryMethods implements SubArticle {
  public void execute() {
    List<String> stringList = List.of("one", "two", "three");
    IO.println("StringList: " + stringList + ", Class: " + stringList.getClass());
    Set<String> stringSet = Set.of("one", "two", "three");
    IO.println("StringSet: " + stringSet + ", Class: " + stringSet.getClass());

    List<String> list = List.copyOf(stringList);
    IO.println("List: " + list + ", Class: " + list.getClass());
    Set<String> set = Set.copyOf(stringList);
    IO.println("Set: " + set + ", Class: " + set.getClass());

    List<String> strings = Arrays.asList("0", "1", "2", "3", "4");
    IO.println(strings);
    int fromIndex = 1, toIndex = 4;
    Collections.rotate(strings.subList(fromIndex, toIndex), -1);
    IO.println(strings);

    List<String> strings1 = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4"));
    List<String> immutableStrings = Collections.unmodifiableList(strings1);
    IO.println(immutableStrings);
    strings1.add("5");
    IO.println(immutableStrings);

  }
}
