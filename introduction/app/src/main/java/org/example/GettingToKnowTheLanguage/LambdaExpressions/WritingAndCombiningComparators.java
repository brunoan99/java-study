package org.example.GettingToKnowTheLanguage.LambdaExpressions;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import static java.util.Comparator.naturalOrder;

import java.util.Arrays;

import org.example.Utils.SubArticle;

public class WritingAndCombiningComparators implements SubArticle {
  public void execute() {
    Comparator<Integer> comparator = Integer::compare;

    Comparator<String> comparatorStringLength = (s1, s2) -> Integer.compare(s1.length(), s2.length());

    Function<String, Integer> toLength = String::length;
    Comparator<String> comparatorStringLength1 = (s1, s2) -> Integer.compare(toLength.apply(s1), toLength.apply(s2));

    Comparator<String> comparatorStringLength2 = Comparator.comparing(String::length);

    Comparator<String> byLengthThenAlphabetically = Comparator.comparing(String::length).thenComparing(naturalOrder());
    List<String> strings = Arrays.asList("one", "two", "three", "four", "five");
    strings.sort(byLengthThenAlphabetically);
    System.out.println(strings);
    strings.sort(byLengthThenAlphabetically.reversed());
    System.out.println(strings);

    List<String> strings1 = Arrays.asList("one", null, "two", "three", null, null, "four", "five");
    Comparator<String> naturalNullsLast = Comparator.nullsLast(naturalOrder());
    strings1.sort(naturalNullsLast);
    System.out.println(strings1);
  }
}
