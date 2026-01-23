package org.example.GettingToKnowTheLanguage.Generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.Utils.SubArticle;

public class Wildcards implements SubArticle {
  public void execute() {

    SubArticle.display("Upper Bounded Wildcards", new UpperBoundedWildcards());

    SubArticle.display("Unbounded Wildcards", new UnboundedWildcards());

    SubArticle.display("Lower Bounded Wildcards", new LowerBoundedWildcard());
  }
}

class UpperBoundedWildcards implements SubArticle {

  static double sumOfList(List<? extends Number> list) {
    double s = 0.0;
    for (Number n : list)
      s += n.doubleValue();
    return s;
  }

  public void execute() {
    List<Integer> li = Arrays.asList(1, 2, 3);
    List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
    System.out.println("Sum Li: " + sumOfList(li));
    System.out.println("Sum Ld: " + sumOfList(ld));
  }
}

class UnboundedWildcards implements SubArticle {

  void printList(List<?> list) {
    for (Object elem : list)
      System.out.print(elem + " ");
    System.out.println();
  }

  public void execute() {
    List<Integer> li = Arrays.asList(1, 2, 3);
    List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
    System.out.print("Li: ");
    printList(li);
    System.out.print("Ld: ");
    printList(ld);
  }
}

class LowerBoundedWildcard implements SubArticle {
  void addNumber(List<? super Integer> list) {
    for (int i = 1; i <= 10; i++) {
      list.add(i);
    }
  }

  public void execute() {
    List<Integer> linteger = new ArrayList<>();
    List<Number> lnumber = new ArrayList<>();
    List<Object> lobject = new ArrayList<>();
    System.out.println("Linteger Before: " + linteger);
    System.out.println("Lnumber Before: " + lnumber);
    System.out.println("Lobject Before: " + lobject);
    addNumber(linteger);
    addNumber(lnumber);
    addNumber(lobject);
    System.out.println("Linteger After: " + linteger);
    System.out.println("Lnumber After: " + lnumber);
    System.out.println("Lobject After: " + lobject);
  }
}
