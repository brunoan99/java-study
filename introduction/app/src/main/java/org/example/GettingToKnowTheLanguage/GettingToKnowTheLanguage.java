package org.example.GettingToKnowTheLanguage;

public class GettingToKnowTheLanguage {
  private static void title(String title) {
    System.out.printf(title + ": \n");
  }

  private static void line() {
    System.out.printf("---------------------------------\n\n\n");
  }

  public static void execute() {
    title("Class");
    Class.execute();
    line();

    title("Inheritance");
    Inheritance.execute();
    line();

    title("Interface");
    Interface.execute();
    line();
  }
}
