package org.example.GettingToKnowTheLanguage.UsingPatternMatching;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.example.Utils.Article;

public class UsingPatternMatching implements Article {
  void print(Object o) {
    if (o instanceof String s) {
      System.out.println("This is a String of length: " + s.length());
    } else {
      System.out.println("This is not a String");
    }
  }

  void printWithPattern(Object o) {
    String formatter = switch (o) {
      case String s when !s.isEmpty() -> String.format("Non-empty string %s", s);
      default -> String.format("Object %s", o.toString());
    };
    System.out.println(formatter);
  }

  record Point(double x, double y) {
  }

  record Box(Object o) {
  }

  void printBox(Object o) {
    switch (o) {
      case Box(String s) -> System.out.println("Box contains the string: " + s);
      case Box(Integer i) -> System.out.println("Box contains the integer: " + i);
      default -> System.out.println("Box contains something else");
    }

  }

  record Circle(Point center, double radius) {
  }

  public void execute() {
    String sonnet = "From fairest creatures we desire increase,\n" +
        "That thereby beauty's rose might never die,\n" +
        "But as the riper should by time decease\n" +
        "His tender heir might bear his memory:\n" +
        "But thou, contracted to thine own bright eyes,\n" +
        "Feed'st thy light's flame with self-substantial fuel,\n" +
        "Making a famine where abundance lies,\n" +
        "Thyself thy foe, to thy sweet self too cruel.\n" +
        "Thou that art now the world's fresh ornament,\n" +
        "And only herald to the gaudy spring,\n" +
        "Within thine own bud buriest thy content,\n" +
        "And, tender churl, mak'st waste in niggardly.\n" +
        "Pity the world, or else this glutton be,\n" +
        "To eat the world's due, by the grave and thee.";

    Pattern pattern = Pattern.compile("\\bflame\\b");
    Matcher matcher = pattern.matcher(sonnet);
    while (matcher.find()) {
      String group = matcher.group();
      int start = matcher.start();
      int end = matcher.end();
      System.out.println(group + " " + start + " " + end);
    }

    print("testing");
    print(10);

    printWithPattern("testing");
    printWithPattern(10);

    Object o = new Point(1.0, 2.0);
    if (o instanceof Point(var x, var y)) {
      System.out.println("X: " + x + ", Y: " + y);
    }

    Object o1 = new Box("testing");
    Object o2 = new Box(1);
    Object o3 = new Box(List.of());
    printBox(o1);
    printBox(o2);
    printBox(o3);

    Object o4 = new Circle(new Point(0.0, 0.0), 0.0); // any object
    if (o4 instanceof Circle(Point(var x, var y), var radius)) {
      System.out.println("X: " + x + ", Y: " + y + ", Radius: " + radius);
    }
  }
}
