package org.example.GettingToKnowTheLanguage;

import org.example.GettingToKnowTheLanguage.ClassesAndObjects.ClassesAndObjects;
import org.example.GettingToKnowTheLanguage.JavaLanguageBasics.JavaLanguageBasics;
import org.example.GettingToKnowTheLanguage.ObjectsClassesInterfacesPackagesAndInheritance.ObjectsClassesInterfacesPackagesAndInheritance;
import org.example.Utils.Article;

public class GettingToKnowTheLanguage {
  private static void title(String title) {
    System.out.printf(title + ": \n");
  }

  private static void line() {
    System.out.printf("---------------------------------\n\n");
  }

  private static void article(String title, Article article) {
    title(title);
    article.execute();
    line();
  }

  public static void execute() {
    article("Objects, Classes, Interfaces, Packages And Inheritance",
        new ObjectsClassesInterfacesPackagesAndInheritance());

    article("Java Language Basics", new JavaLanguageBasics());

    article("Classes and Objects", new ClassesAndObjects());
  }
}
