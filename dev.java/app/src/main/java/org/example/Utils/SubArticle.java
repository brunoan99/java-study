package org.example.Utils;

public interface SubArticle {
  public void execute();

  final String lineFormatString = "-  -  -  -  -  -  -  ";

  private static void line() {
    System.out.println(lineFormatString);
  }

  private static void title(String title) {
    System.out.printf(title + ": \n");
  }

  public static void display(String title, SubArticle subArticle) {
    title(title);
    subArticle.execute();
    line();
  }
}
