package org.example.GettingToKnowTheLanguage.NumbersAndStrings;

import org.example.Utils.SubArticle;

public class StringBuilders implements SubArticle {
  public void execute() {
    SubArticle.display("StringBuilder in Action", new StringBuilderDemo());
  }
}

class StringBuilderDemo implements SubArticle {
  public void execute() {
    String palindrome = "Dot saw I was Tod";

    StringBuilder sb = new StringBuilder(palindrome);

    sb.reverse(); // reverse it
    System.out.println(sb);
  }
}
