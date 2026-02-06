package org.example.MasteringTheAPI.TheCollectionsFramework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.example.Utils.Article;
import org.example.Utils.SubArticle;

public class StoringElementsInACollection implements SubArticle {
  public void execute() {
    Article.display("Methods That Handle Individual Elements", new MethodsThatHandleIndividualElements());

    Article.display("Methods That Handle Other Collections", new MethodsThatHandleOtherCollections());

    Article.display("Methods That Handle The Collection Itself", new MethodsThatHandleTheCollectionItself());

    Article.display("Getting An Array Of The Elements Of A Collection", new GettingAnArrayOfTheElementsOfACollection());

    Article.display("Filtering out Elements of a Collection with a Predicate",
        new FilteringOutElementsOfACollectionWithAPredicate());
  }

}

class MethodsThatHandleIndividualElements implements SubArticle {
  public void execute() {
    Collection<String> strings = new ArrayList<>();
    strings.add("one");
    strings.add("two");
    System.out.println("strings = " + strings);
    strings.remove("one");
    System.out.println("strings = " + strings);
    strings.add("one");
    if (strings.contains("one")) {
      System.out.println("one is here");
    }
    if (!strings.contains("three")) {
      System.out.println("three is not here");
    }
  }
}

class MethodsThatHandleOtherCollections implements SubArticle {
  public void execute() {
    Collection<String> strings = new ArrayList<>();
    strings.add("one");
    strings.add("two");
    strings.add("three");

    Collection<String> first = new ArrayList<>();
    first.add("one");
    first.add("two");

    Collection<String> second = new ArrayList<>();
    second.add("one");
    second.add("four");

    Collection<String> third = new ArrayList<>();
    third.add("one");
    third.add("five");

    System.out.println("Is first contained in strings? " + strings.containsAll(first));
    System.out.println("Is second contained in strings? " + strings.containsAll(second));

    strings.clear();
    strings.add("one");
    strings.add("two");
    strings.add("three");
    boolean hasChanged = strings.addAll(second);
    System.out.println("Has strings changed? " + hasChanged);
    System.out.println("strings = " + strings);

    Collection<String> toBeRemoved = new ArrayList<>();
    toBeRemoved.add("one");
    toBeRemoved.add("four");
    strings.clear();
    strings.add("one");
    strings.add("two");
    strings.add("three");
    boolean hasChanged1 = strings.removeAll(toBeRemoved);
    System.out.println("Has strings changed? " + hasChanged1);
    System.out.println("strings = " + strings);

    strings.clear();
    strings.add("one");
    strings.add("two");
    strings.add("three");
    boolean hasChanged2 = strings.retainAll(third);
    System.out.println("Has strings changed? " + hasChanged2);
    System.out.println("strings = " + strings);
  }
}

class MethodsThatHandleTheCollectionItself implements SubArticle {
  public void execute() {
    Collection<String> strings = new ArrayList<>();
    strings.add("one");
    strings.add("two");
    if (!strings.isEmpty()) {
      IO.println("Indeed strings is not empty!");
    }
    IO.println("The number of elements in strings is " + strings.size());
    strings.clear();
    IO.println("After clearing it, this number is now " + strings.size());
  }
}

class GettingAnArrayOfTheElementsOfACollection implements SubArticle {
  public void execute() {
    Collection<String> strings = List.of("one", "two");

    String[] largerTab = { "three", "three", "three", "I", "was", "there" };
    IO.println("largerTab = " + Arrays.toString(largerTab));

    String[] result = strings.toArray(largerTab);
    IO.println("result = " + Arrays.toString(result));

    IO.println("Same arrays? " + (result == largerTab));

    String[] zeroLengthTab = {};
    String[] result1 = strings.toArray(zeroLengthTab);

    IO.println("zeroLengthTab = " + Arrays.toString(zeroLengthTab));
    IO.println("result = " + Arrays.toString(result1));
  }
}

class FilteringOutElementsOfACollectionWithAPredicate implements SubArticle {
  public void execute() {
    Predicate<String> isNull = Objects::isNull;
    Predicate<String> isEmpty = String::isEmpty;
    Predicate<String> isNullOrEmpty = isNull.or(isEmpty);

    Collection<String> strings = new ArrayList<>();
    strings.add(null);
    strings.add("");
    strings.add("one");
    strings.add("two");
    strings.add("");
    strings.add("three");
    strings.add(null);

    IO.println("strings = " + strings);
    strings.removeIf(isNullOrEmpty);
    IO.println("filtered strings = " + strings);
  }
}
