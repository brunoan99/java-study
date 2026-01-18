package org.example.GettingToKnowTheLanguage.ClassesAndObjects;

import org.example.Utils.Article;

public class ClassesAndObjects implements Article {
  private static void title(String title) {
    System.out.printf(title + ": \n");
  }

  private static void line() {
    System.out.printf("- - - - - - - - - - - - - - - - -\n\n");
  }

  private static void article(String title, Article article) {
    title(title);
    article.execute();
    line();
  }

  public void execute() {
    article("Creating Classes", new CreatingClasses());

    article("Defining Methods", new DefiningMethods());

    article("Providing Constructors for your Classes", new ProvidingConstructorsForYouClasses());

    article("Creating and Using Objects", new CreatingAndUsingObjects());

    article("More on Classes", new MoreOnClasses());

    article("Nested Classes", new NestedClasses());

    article("Enums", new Enums());
  }
}
