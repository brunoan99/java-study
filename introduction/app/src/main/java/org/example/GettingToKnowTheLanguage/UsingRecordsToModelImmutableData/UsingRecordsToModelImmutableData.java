package org.example.GettingToKnowTheLanguage.UsingRecordsToModelImmutableData;

import java.util.List;

import org.example.Utils.Article;

public class UsingRecordsToModelImmutableData implements Article {
  public void execute() {
    Point p1 = new Point(0, 0);
    System.out.println("Point P1: " + p1);

    Range r1 = new Range(-5, 10);
    System.out.println("Range R1: " + r1);

    State s1 = new State("ST1", "STC1", List.of());
    System.out.println("State S1: " + s1);
  }
}

// class Point {
// private final int x;
// private final int y;

// public Point(int x, int y) {
// this.x = x;
// this.y = y;
// }
// }
record Point(int x, int y) {
}

record Range(int start, int end) {
  Range {
    if (end <= start)
      throw new IllegalArgumentException("End cannot be lesser than start");
    if (start < 0)
      start = 0;
    if (end < 0)
      end = 0;
  }
}

record State(String name, String capitalCity, List<String> cities) {
  public State {
    cities = List.copyOf(cities);
  }

  public State(String name, String capitalCity) {
    this(name, capitalCity, List.of());
  }

  public State(String name, String capitalCity, String... cities) {
    this(name, capitalCity, List.of(cities));
  }

  public List<String> cities() {
    return List.copyOf(cities);
  }
}
