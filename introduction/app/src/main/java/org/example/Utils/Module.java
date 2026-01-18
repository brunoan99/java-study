package org.example.Utils;

public interface Module {
  public void execute();

  final String lineFormatString = "---------------------\n\n";

  private static void line() {
    System.out.println(lineFormatString);
  }

  private static void title(String title) {
    System.out.printf(title + ": \n");
  }

  public static void display(String title, Article article) {
    title(title);
    article.execute();
    line();
  }
}
