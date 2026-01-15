package org.example.GettingToKnowTheLanguage;

import org.example.Utils.Article;

public class BranchingWithSwitchStatementsAndExpressions implements Article {
  enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
  }

  public void execute() {
    String month = "March"; // any month
    int monthNumber = -1;

    switch (month.toLowerCase()) {
      case "january":
        monthNumber = 1;
        break;
      case "february":
        monthNumber = 2;
        break;
      case "march":
        monthNumber = 3;
        break;
      case "april":
        monthNumber = 4;
        break;
      case "may":
        monthNumber = 5;
        break;
      case "june":
        monthNumber = 6;
        break;
      case "july":
        monthNumber = 7;
        break;
      case "august":
        monthNumber = 8;
        break;
      case "september":
        monthNumber = 9;
        break;
      case "october":
        monthNumber = 10;
        break;
      case "november":
        monthNumber = 11;
        break;
      case "december":
        monthNumber = 12;
        break;
      default:
        monthNumber = 0;
        break;
    }
    System.out.println("MonthNumber: " + monthNumber);

    Day day = Day.MONDAY;
    int len = switch (day) {
      case MONDAY, FRIDAY, SUNDAY -> 6;
      case TUESDAY -> 7;
      case THURSDAY, SATURDAY -> 8;
      case WEDNESDAY -> 9;
    };
    System.out.println("Len: " + len);
  }
}
