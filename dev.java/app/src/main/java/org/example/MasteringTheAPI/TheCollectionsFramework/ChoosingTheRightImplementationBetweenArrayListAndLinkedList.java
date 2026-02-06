package org.example.MasteringTheAPI.TheCollectionsFramework;

import org.example.Utils.SubArticle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.Utils.PrintUtil;

public class ChoosingTheRightImplementationBetweenArrayListAndLinkedList implements SubArticle {
  public void execute() {
    IO.println("\nAlgorithm Complexity");

    List<List<String>> table = new ArrayList<>();
    table.add(Arrays.asList("Operation", "ArrayList", "LinkedList"));
    table.add(Arrays.asList("Reading first", "O(1)", "O(1)"));
    table.add(Arrays.asList("Reading last", "O(1)", "O(1)"));
    table.add(Arrays.asList("Reading middle", "O(1)", "O(n)"));
    table.add(Arrays.asList("Inserting first", "O(n)", "O(1)"));
    table.add(Arrays.asList("Inserting last", "O(1)", "O(1)"));
    table.add(Arrays.asList("Inserting middle", "O(n)", "O(n)"));

    IO.println(PrintUtil.formatAsTable(table));
  }
}
