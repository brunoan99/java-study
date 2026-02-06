package org.example.GettingToKnowTheLanguage.Generics;

import org.example.Utils.Article;

public class Generics implements Article {
  public void execute() {
    Article.display("Introducing Generics", new IntroducingGenerics());

    Article.display("Wildcards", new Wildcards());

    Article.display("Type Erasure", new TypeErasure());

    Article.display("Restriction on Generics", new RestrictionOnGenerics());
  }
}
