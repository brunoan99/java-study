package org.example.GettingToKnowTheLanguage.NumbersAndStrings;

import java.util.ArrayList;
import java.util.List;

import org.example.Utils.SubArticle;

public class AutoboxingAndUnboxing implements SubArticle {
  public void execute() {
    SubArticle.display("Autoboxing", new Autoboxing());

    SubArticle.display("Unboxing", new Unboxing());
  }
}

class Autoboxing implements SubArticle {
  public void execute() {
    Character ch = 'a';
    System.out.println("Ch: " + ch + ",\n  Class: " + ch.getClass());

    List<Integer> ints = new ArrayList<>();
    for (int i = 1; i < 50; i += 2)
      ints.add(i);
    System.out.println("Ints: " + ints + ",\n  Class: " + ints.get(0).getClass());

  }
}

class Unboxing implements SubArticle {

  public void execute() {
    Integer i = Integer.valueOf(-8);

    // 1. Unboxing through method invocation
    int absVal = absoluteValue(i);
    System.out.println("absolute value of " + i + " = " + absVal);

    List<Double> doubles = new ArrayList<>();
    doubles.add(3.1416); // Π is autoboxed through method invocation.

    // 2. Unboxing through assignment
    double pi = doubles.get(0);
    System.out.println("pi = " + pi);
  }

  public static int absoluteValue(int i) {
    return (i < 0) ? -i : i;
  }
}
