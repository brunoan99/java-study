package org.example.GettingToKnowTheLanguage.NumbersAndStrings;

import org.example.Utils.Article;

public class NumbersAndStrings implements Article {

  public void execute() {
    Article.display("Numbers", new Numbers());

    Article.display("Characters", new Characters());

    Article.display("Strings", new Strings());

    Article.display("String Builders", new StringBuilders());

    Article.display("Autoboxing and Unboxing", new AutoboxingAndUnboxing());
  }
}
