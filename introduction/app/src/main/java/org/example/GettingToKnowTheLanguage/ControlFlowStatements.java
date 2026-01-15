package org.example.GettingToKnowTheLanguage;

import org.example.Utils.Article;

public class ControlFlowStatements implements Article {
  private static void title(String title) {
    System.out.printf(title + ": \n");
  }

  private static void line() {
    System.out.printf("-  -  -  -  -  -  -  -  -  -  -  \n\n");
  }

  private static void article(String title, Article article) {
    title(title);
    article.execute();
    line();
  }

  public void execute() {
    article("If Else Statements", new IfElseDemo());

    article("While Statements", new WhileDemo());

    article("Do While Statements", new DoWhileDemo());

    article("For Statements", new ForDemo());

    article("Enhanced For Statements", new EnhancedForDemo());

    article("Break Statements", new BreakDemo());

    article("Break With Label Statements", new BreakWithLabelDemo());

    article("Continue Statements", new ContinueDemo());

    article("Continue With Label Statements", new ContinueWithLabelDemo());

    article("Yield Statements", new YieldDemo());
  }
}

class IfElseDemo implements Article {
  public void execute() {
    int testscore = 76;
    char grade;

    if (testscore >= 90) {
      grade = 'A';
    } else if (testscore >= 80) {
      grade = 'B';
    } else if (testscore >= 70) {
      grade = 'C';
    } else if (testscore >= 60) {
      grade = 'D';
    } else {
      grade = 'F';
    }
    System.out.println("Grade = " + grade);
  }
}

class WhileDemo implements Article {
  public void execute() {
    int count = 1;
    while (count < 11) {
      System.out.println("Count is: " + count);
      count++;
    }
  }
}

class DoWhileDemo implements Article {
  public void execute() {
    int count = 1;
    do {
      System.out.println("Count is: " + count);
      count++;
    } while (count < 11);
  }
}

class ForDemo implements Article {
  public void execute() {
    for (int i = 1; i < 11; i++) {
      System.out.println("Count is: " + i);
    }
    // for (;;;) infinite loop
  }
}

class EnhancedForDemo implements Article {
  public void execute() {
    int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    for (int item : numbers) {
      System.out.println("Count is: " + item);
    }
  }
}

class BreakDemo implements Article {
  public void execute() {

    int[] arrayOfInts = { 32, 87, 3, 589,
        12, 1076, 2000,
        8, 622, 127 };
    int searchfor = 12;

    int i;
    boolean foundIt = false;

    for (i = 0; i < arrayOfInts.length; i++) {
      if (arrayOfInts[i] == searchfor) {
        foundIt = true;
        break;
      }
    }

    if (foundIt) {
      System.out.println("Found " + searchfor + " at index " + i);
    } else {
      System.out.println(searchfor + " not in the array");
    }
  }
}

class BreakWithLabelDemo implements Article {
  public void execute() {

    int[][] arrayOfInts = {
        { 32, 87, 3, 589 },
        { 12, 1076, 2000, 8 },
        { 622, 127, 77, 955 }
    };
    int searchfor = 12;

    int i;
    int j = 0;
    boolean foundIt = false;

    search: for (i = 0; i < arrayOfInts.length; i++) {
      for (j = 0; j < arrayOfInts[i].length; j++) {
        if (arrayOfInts[i][j] == searchfor) {
          foundIt = true;
          break search;
        }
      }
    }

    if (foundIt) {
      System.out.println("Found " + searchfor + " at " + i + ", " + j);
    } else {
      System.out.println(searchfor + " not in the array");
    }
  }
}

class ContinueDemo implements Article {
  public void execute() {

    String searchMe = "peter piper picked a " + "peck of pickled peppers";
    int max = searchMe.length();
    int numPs = 0;

    for (int i = 0; i < max; i++) {
      // interested only in p's
      if (searchMe.charAt(i) != 'p')
        continue;

      // process p's
      numPs++;
    }
    System.out.println("Found " + numPs + " p's in the string.");
  }
}

class ContinueWithLabelDemo implements Article {
  public void execute() {

    String searchMe = "Look for a substring in me";
    String substring = "sub";
    boolean foundIt = false;

    int max = searchMe.length() -
        substring.length();

    test: for (int i = 0; i <= max; i++) {
      int n = substring.length();
      int j = i;
      int k = 0;
      while (n-- != 0) {
        if (searchMe.charAt(j++) != substring.charAt(k++)) {
          continue test;
        }
      }
      foundIt = true;
      break test;
    }
    System.out.println(foundIt ? "Found it" : "Didn't find it");
  }
}

class YieldDemo implements Article {
  enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
  }

  private int calculate(Day d) {
    return switch (d) {
      case SATURDAY, SUNDAY -> 0;
      default -> {
        int remainingWorkDays = 5 - d.ordinal();
        yield remainingWorkDays;
      }
    };
  }

  public void execute() {
    System.out.println(calculate(Day.THURSDAY));
  }
}
