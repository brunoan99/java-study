package org.example.GettingToKnowTheLanguage;

import java.util.List;

import org.example.Utils.Article;

public class UsingTheVarTypeIdentifier implements Article {
  public void execute() {
    var list = List.of("one", "two", "three", "four");
    for (var element : list) {
      System.out.println(element);
    }
    System.out.println();

    // var path = Path.of("debug.log");
    // try (var stream = Files.newInputStream(path)) {
    // // process the file
    // }
    System.out.println("Variables Restrictions: \n" +
        "  - only local variables \n" +
        "  - var cannot be used on fields \n" +
        "  - var cannot be used on method or constructor parameters \n" +
        "  - can have a strict type available at compiler time\n\n");
  }
}
