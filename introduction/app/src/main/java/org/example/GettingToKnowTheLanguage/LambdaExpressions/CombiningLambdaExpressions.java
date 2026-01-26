package org.example.GettingToKnowTheLanguage.LambdaExpressions;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;

import org.example.Utils.SubArticle;

public class CombiningLambdaExpressions implements SubArticle {
  public void execute() {
    // Predicate<String> p = s -> (s != null) && !s.isEmpty() && s.length() < 5;

    // Predicate<String> nonNull = s -> s != null;
    // Predicate<String> nonEmpty = s -> !s.isEmpty();
    // Predicate<String> shorterThan5 = s -> s.length() < 5;
    // Predicate<String> p = nonNull.and(nonEmpty).and(shorterThan5);

    Predicate<String> isNull = Objects::isNull;
    Predicate<String> isEmpty = String::isEmpty;
    Predicate<String> isNullOrEmpty = isNull.or(isEmpty);
    Predicate<String> isNotNullNorEmpty = isNullOrEmpty.negate();
    Predicate<String> shorterThan5 = s -> s.length() < 5;

    Predicate<String> p = isNotNullNorEmpty.and(shorterThan5);

    Logger logger = Logger.getLogger("MyApplicationLogger");
    Consumer<String> log = logger::info;
    Consumer<String> print = System.out::println;

    Consumer<String> logAndPrint = log.andThen(print);
    logAndPrint.accept("Testing Logger and Print");
  }
}
