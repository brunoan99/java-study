package org.example.GettingToKnowTheLanguage.ClassesAndObjects;

import org.example.Utils.Article;

public class ClassesAndObjects implements Article {

  public void execute() {
    Article.display("Creating Classes", new CreatingClasses());

    Article.display("Defining Methods", new DefiningMethods());

    Article.display("Providing Constructors for your Classes", new ProvidingConstructorsForYouClasses());

    Article.display("Creating and Using Objects", new CreatingAndUsingObjects());

    Article.display("More on Classes", new MoreOnClasses());

    Article.display("Nested Classes", new NestedClasses());

    Article.display("Enums", new Enums());
  }
}
