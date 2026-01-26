package org.example.GettingToKnowTheLanguage.LambdaExpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.example.Utils.SubArticle;

public class UsingLambdaExpressions implements SubArticle {
  public void execute() {
    SubArticle.display("Creating or Providing Objects with a Supplier", new ProvidingObjectsWithSupplier());

    SubArticle.display("Consuming Objects with Consumer", new ConsumingObjectsWithConsumer());

    SubArticle.display("Testing Objects with Predicate", new TestingObjectsWithPredicate());

    SubArticle.display("Mapping Objects to Other Objects with Function",
        new MappingObjectsToOtherObjectsWithFunction());
  }
}

class ProvidingObjectsWithSupplier implements SubArticle {
  @FunctionalInterface
  interface Supplier<T> {
    T get();
  }

  public void execute() {
    Supplier<String> supplier = () -> "Hello Duke!";
    System.out.println("Supplier Get: " + supplier.get());

    Random random = new Random(314L);
    Supplier<Integer> newRandom = () -> random.nextInt(10);

    for (int index = 0; index < 5; index++) {
      System.out.println("Random-" + index + ": " + newRandom.get());
    }
  }
}

class ConsumingObjectsWithConsumer implements SubArticle {

  public void execute() {
    Consumer<String> printer = s -> System.out.println(s);

    Random random = new Random(314L);
    IntSupplier newRandom = () -> random.nextInt(10);
    for (int index = 0; index < 5; index++) {
      printer.accept("RandomInt-" + index + ": " + newRandom.getAsInt());
    }

    BiConsumer<Random, Integer> randomNumberPrinter = (randomGen, number) -> {
      for (int index = 0; index < number; index++) {
        printer.accept("RandomGenInt-" + index + ": " + randomGen.nextInt(10));
      }
    };
    randomNumberPrinter.accept(new Random(314L), 5);

    List<String> immutableStrings = List.of("one", "two", "three", "four", "five");
    List<String> strings = new ArrayList<>(immutableStrings);
    strings.forEach(printer);
  }
}

class TestingObjectsWithPredicate implements SubArticle {
  public void execute() {
    Predicate<String> length3 = s -> s.length() == 3;
    String word = "any";
    boolean isOfLength3 = length3.test(word);
    System.out.println("Is of length 3? " + isOfLength3);

    IntPredicate isGreaterThan10 = i -> i > 10;
    System.out.println("Is greater than 10? " + isGreaterThan10.test(9));

    BiPredicate<String, Integer> isOfLength = (w, l) -> w.length() == l;
    System.out.println("Is Word of Length 3? " + isOfLength.test(word, 3));

    List<String> immutableStrings = List.of("one", "two", "three", "four", "five");
    List<String> strings = new ArrayList<>(immutableStrings);
    Predicate<String> isEvenLength = s -> s.length() % 2 == 0;
    strings.removeIf(isEvenLength);
    System.out.println("Strings: " + strings);

  }
}

class MappingObjectsToOtherObjectsWithFunction implements SubArticle {
  public void execute() {
    Function<String, Integer> toLength = s -> s.length();
    String word = "any";
    int length = toLength.apply(word);
    System.out.println("Word: " + word + ", Length: " + length);

    List<String> strings = Arrays.asList("one", "two", "three");
    System.out.println("Strings Before Uppercase: " + strings);
    UnaryOperator<String> toUpperCase = w -> w.toUpperCase();
    strings.replaceAll(toUpperCase);
    System.out.println("Strings After Uppercase: " + strings);

    BiFunction<String, String, Integer> findWordInSentence = (w, s) -> s.indexOf(w);
    String s = "Sentence that contain a word";
    String w = "contain";
    Integer i = findWordInSentence.apply(w, s);
    System.out.println("Sentence: " + s + ", Word: " + w + ", FindAt: " + i);
  }
}
