package org.example.GettingToKnowTheLanguage.ClassesAndObjects;

import org.example.Utils.Article;

public class Enums implements Article {
  public void execute() {
    new EnumsDemo().execute();
  }

}

enum DayOfWeek {
  MONDAY("MON"), TUESDAY("TUE"), WEDNESDAY("WED"), THURSDAY("THU"), FRIDAY("FRI"), SATURDAY("SAT"), SUNDAY("SUN");

  private final String abbreviation;

  DayOfWeek(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public String getAbbreviation() {
    return abbreviation;
  }
}

class EnumsDemo implements Article {
  public void execute() {
    DayOfWeek weekStart = DayOfWeek.MONDAY;

    if (weekStart == DayOfWeek.MONDAY)
      System.out.println("The week starts on Monday");

    DayOfWeek someDay = DayOfWeek.FRIDAY;

    // switch (someDay) {
    // case MONDAY ->
    // System.out.println("The week just started.");
    // case TUESDAY, WEDNESDAY, THURSDAY ->
    // System.out.println("We are somewhere in the middle of the week.");
    // case FRIDAY ->
    // System.out.println("The weekend is near.");
    // case SATURDAY, SUNDAY ->
    // System.out.println("Weekend");
    // default ->
    // throw new AssertionError("Should not happen");
    // }
    System.out.println(switch (someDay) {
      case MONDAY -> "The week just started.";
      case TUESDAY, WEDNESDAY, THURSDAY -> "We are somewhere in the middle of the week.";
      case FRIDAY -> "The weekend is near.";
      case SATURDAY, SUNDAY -> "Weekend";
    });

    System.out.println(DayOfWeek.MONDAY.name());
    System.out.println(DayOfWeek.MONDAY.ordinal());

    DayOfWeek[] days = DayOfWeek.values();
    DayOfWeek monday = DayOfWeek.valueOf("MONDAY");
    System.out.print("Days: [");
    for (DayOfWeek day : days) {
      System.out.print(" " + day);
    }
    System.out.println("]");
    System.out.println(monday);
  }
}
