package org.example.GettingToKnowTheLanguage.LambdaExpressions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;

import org.example.Utils.SubArticle;

public class WritingLambdaExpressionsAsMethodReferences implements SubArticle {
  public void execute() {
    // Consumer<String> printer = s -> System.out.println();
    Consumer<String> printer = System.out::println;

    // DoubleUnaryOperator sqrt = a -> Math.sqrt(a);
    DoubleUnaryOperator sqrt = Math::sqrt;

    // IntBinaryOperator max = (a, b) -> Integer.max(a, b);
    IntBinaryOperator max = Integer::max;

    // Function<String, Integer> toLength = s -> s.length();
    Function<String, Integer> toLength = String::length;

    // BiFunction<String, String, Integer> indexOf = (sentence, word) ->
    // sentence.indexOf(word);
    BiFunction<String, String, Integer> indexOf = String::indexOf;

    // Supplier<List<String>> newListOfStrings = () -> new ArrayList<>();
    Supplier<List<String>> newListOfStrings = ArrayList::new;
    Function<Integer, List<String>> newListOfNStrings = size -> new ArrayList<>(size);
  }
}
