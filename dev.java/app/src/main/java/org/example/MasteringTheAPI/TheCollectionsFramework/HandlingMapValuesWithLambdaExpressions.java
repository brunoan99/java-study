package org.example.MasteringTheAPI.TheCollectionsFramework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.Utils.SubArticle;

public class HandlingMapValuesWithLambdaExpressions implements SubArticle {
  public void execute() {
    Map<Integer, String> map = new HashMap<>();
    map.put(1, "one");
    map.put(2, "two");
    map.put(3, "three");

    map.forEach((key, value) -> IO.println(key + " :: " + value));

    // Basic forEach with BiConsumer
    Map<String, Integer> scores = new HashMap<>();
    scores.put("Alice", 95);
    scores.put("Bob", 87);
    scores.put("Carol", 92);
    scores.put("David", 78);

    IO.println("Original Scores:");
    scores.forEach((name, score) -> IO.println(name + ": " + score));

    // Replace operations
    scores.replace("Alice", 97); // Replace existing value
    scores.replace("Eve", 85); // Won't replace (key doesn't exist)
    scores.replace("Bob", 87, 90); // Replace only if current value matches

    IO.println("");
    IO.println("After specific replacements:");
    scores.forEach((name, score) -> IO.println(name + ": " + score));

    // ReplaceAll with BiFunction - give everyone bonus points!
    scores.replaceAll((name, score) -> score + 5);

    IO.println("");
    IO.println("After adding 5 bonus points to everyone:");
    scores.forEach((name, score) -> IO.println(name + ": " + score));

    IO.println();
    Map<Integer, String> map1 = new HashMap<>();

    map1.put(1, "one");
    map1.put(2, "two");
    map1.put(3, "three");

    map1.replaceAll((key, value) -> value.toUpperCase());
    map1.forEach((key, value) -> IO.println(key + " :: " + value));

    List<String> strings = List.of("one", "two", "three", "four", "five", "six", "seven");
    Map<Integer, List<String>> map2 = new HashMap<>();
    for (String word : strings) {
      int length = word.length();
      if (!map2.containsKey(length)) {
        map2.put(length, new ArrayList<>());
      }
      map2.get(length).add(word);
    }

    map2.forEach((key, value) -> IO.println(key + " :: " + value));

    Map<Integer, List<String>> map3 = new HashMap<>();
    for (String word : strings) {
      int length = word.length();
      map3.putIfAbsent(length, new ArrayList<>());
      map3.get(length).add(word);
    }
    map3.forEach((key, value) -> IO.println(key + " :: " + value));

    Map<Integer, List<String>> map4 = new HashMap<>();
    for (String word : strings) {
      int length = word.length();
      map4.computeIfAbsent(length, key -> new ArrayList<>())
          .add(word);
    }
    map4.forEach((key, value) -> IO.println(key + " :: " + value));

    // Setup initial map
    Map<String, Integer> inventory = new HashMap<>();
    inventory.put("apples", 50);
    inventory.put("bananas", 30);
    inventory.put("oranges", 25);

    IO.println("Initial inventory:");
    inventory.forEach((item, count) -> IO.println(item + ": " + count));

    // compute - always executes, can handle null values
    inventory.compute("apples", (item, count) -> count != null ? count + 20 : 20);

    // computeIfPresent - only if key exists and value is not null
    inventory.computeIfPresent("bananas", (item, count) -> count - 5);

    // computeIfAbsent - only if key doesn't exist or value is null
    inventory.computeIfAbsent("grapes", item -> 15);

    IO.println("");
    IO.println("After compute operations:");
    inventory.forEach((item, count) -> IO.println(item + ": " + count));

    // Try more examples
    inventory.computeIfPresent("nonexistent", (item, count) -> 999); // Won't execute
    inventory.computeIfAbsent("pears", item -> 12); // Will execute

    IO.println("");
    IO.println("Final inventory:");
    inventory.forEach((item, count) -> IO.println(item + ": " + count));

    // Group words by their length
    List<String> words = Arrays.asList("java", "python", "go", "rust", "c", "swift", "kotlin", "html", "css");
    Map<Integer, List<String>> wordsByLength = new HashMap<>();

    for (String word : words) {
      wordsByLength.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word);
    }

    IO.println("Words grouped by length:");
    wordsByLength.forEach((length, wordList) -> IO.println(length + " letters: " + wordList));

    // Group students by grade
    String[] students = { "Alice-A", "Bob-B", "Carol-A", "David-C", "Eve-B", "Frank-A" };
    Map<String, List<String>> studentsByGrade = new HashMap<>();

    for (String student : students) {
      String[] parts = student.split("-");
      String name = parts[0];
      String grade = parts[1];

      studentsByGrade.computeIfAbsent(grade, k -> new ArrayList<>()).add(name);
    }

    IO.println("");
    IO.println("Students grouped by grade:");
    studentsByGrade.forEach((grade, studentList) -> IO.println("Grade " + grade + ": " + studentList));

    IO.println("");
    IO.println("Merging Values:");
    List<String> strings1 = List.of("one", "two", "three", "four", "five", "six", "seven");
    Map<Integer, String> map5 = new HashMap<>();
    for (String word : strings1) {
      int length = word.length();
      map5.merge(length, word,
          (existingValue, newWord) -> existingValue + ", " + newWord);
    }

    map5.forEach((key, value) -> IO.println(key + " :: " + value));

    // Character counting with merge
    String text = "hello world programming";
    Map<Character, Integer> charCount = new HashMap<>();

    for (char c : text.toCharArray()) {
      if (c != ' ') { // Skip spaces
        charCount.merge(c, 1, (oldCount, newCount) -> oldCount + newCount);
      }
    }

    IO.println("Character frequencies:");
    charCount.forEach((character, count) -> IO.println("'" + character + "': " + count));

    // Word counting
    String[] sentence = { "the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog" };
    Map<String, Integer> wordCount = new HashMap<>();

    for (String word : sentence) {
      wordCount.merge(word, 1, Integer::sum); // Using method reference
    }

    IO.println("\nWord frequencies:");
    wordCount.forEach((word, count) -> IO.println("'" + word + "': " + count));

    // Combining maps with merge
    Map<String, Integer> sales1 = Map.of("Product A", 100, "Product B", 150);
    Map<String, Integer> sales2 = Map.of("Product B", 75, "Product C", 200);
    Map<String, Integer> totalSales = new HashMap<>(sales1);

    sales2.forEach((product, amount) -> totalSales.merge(product, amount, Integer::sum));

    IO.println("\nCombined sales:");
    totalSales.forEach((product, total) -> IO.println(product + ": " + total));
    // Character counting with merge
    String text1 = "hello world programming";
    Map<Character, Integer> charCount1 = new HashMap<>();

    for (char c : text1.toCharArray()) {
      if (c != ' ') { // Skip spaces
        charCount1.merge(c, 1, (oldCount, newCount) -> oldCount + newCount);
      }
    }

    IO.println("\nCharacter frequencies:");
    charCount1.forEach((character, count) -> IO.println("'" + character + "': " + count));

    // Word counting
    String[] sentence1 = { "the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog" };
    Map<String, Integer> wordCount1 = new HashMap<>();

    for (String word : sentence1) {
      wordCount1.merge(word, 1, Integer::sum); // Using method reference
    }

    IO.println("");
    IO.println("Word frequencies:");
    wordCount1.forEach((word, count) -> IO.println("'" + word + "': " + count));

    // Combining maps with merge
    Map<String, Integer> sales3 = Map.of("Product A", 100, "Product B", 150);
    Map<String, Integer> sales4 = Map.of("Product B", 75, "Product C", 200);
    Map<String, Integer> totalSales1 = new HashMap<>(sales3);

    sales4.forEach((product, amount) -> totalSales1.merge(product, amount, Integer::sum));

    IO.println("");
    IO.println("Combined sales:");
    totalSales1.forEach((product, total) -> IO.println(product + ": " + total));
  }
}
