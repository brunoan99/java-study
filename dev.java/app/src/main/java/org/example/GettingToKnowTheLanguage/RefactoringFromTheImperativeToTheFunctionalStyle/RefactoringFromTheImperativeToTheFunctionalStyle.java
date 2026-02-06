package org.example.GettingToKnowTheLanguage.RefactoringFromTheImperativeToTheFunctionalStyle;

import java.util.List;
import java.util.stream.IntStream;

import org.example.Utils.Article;

public class RefactoringFromTheImperativeToTheFunctionalStyle implements Article {
  public void execute() {
  }
}

class ConvertingSimpleLoops {
  void imperative() {
    for (int i = 0; i < 5; i++) {
      System.out.println(i);
    }
  }

  void functional() {
    IntStream.range(0, 5).forEach(System.out::println);
  }
}

class ConvertingSimpleLoopsWithSteps {
  void imperative() {
    for (int i = 0; i < 15; i = i + 3) {
      System.out.println(i);
    }
  }

  void functional() {
    IntStream.iterate(0, i -> i < 15, i -> i + 3)
        .forEach(System.out::println);
  }
}

class UnboundedIterationWithBreak {
  void imperative() {
    for (int i = 0;; i = i + 3) {
      if (i > 20) {
        break;
      }
      System.out.println(i);
    }
  }

  void functional() {
    IntStream.iterate(0, i -> i + 3)
        .takeWhile(i -> i <= 20)
        .forEach(System.out::println);
  }
}

class IteratingWithIf {
  List<String> names = List.of("Jack", "Paula", "Kate", "Peter");

  void imperative() {
    for (String name : names) {
      if (name.length() == 4) {
        System.out.println(name);
      }
    }
  }

  void functional() {
    names.stream()
        .filter(name -> name.length() == 4)
        .forEach(name -> System.out.println(name));
  }
}

class TransformingWhileIterating {
  List<String> names = List.of("Jack", "Paula", "Kate", "Peter");

  void imperative() {
    for (String name : names) {
      System.out.println(name.toUpperCase());
    }
  }

  void functional() {
    names.stream()
        .map(name -> name.toUpperCase())
        .forEach(nameInUpperCase -> System.out.println(nameInUpperCase));
  }
}

class PickingElementsToTransform {
  List<String> names = List.of("Jack", "Paula", "Kate", "Peter");

  void imperative() {
    for (String name : names) {
      if (name.length() == 4) {
        System.out.println(name.toUpperCase());
      }
    }
  }

  void functional() {
    names.stream()
        .filter(name -> name.length() == 4)
        .map(String::toUpperCase)
        .forEach(System.out::println);
  }

}
