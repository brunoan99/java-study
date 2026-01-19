package org.example.GettingToKnowTheLanguage.NumbersAndStrings;

import org.example.Utils.SubArticle;

public class Characters implements SubArticle {
  public void execute() {
    SubArticle.display("Escape Sequence", new EscapeDemo());
  }
}

class EscapeDemo implements SubArticle {
  public void execute() {
    System.out.println(
        "Escape Sequence:.\n" +
            "  - \\t: Insert a tab in the text at this point.\n" +
            "  - \\b: Insert a backspace in the text at this point..\n" +
            "  - \\n: Insert a newline in the text at this point.\n" +
            "  - \\r: Insert carriage return a in the text at this point.\n" +
            "  - \\f: Insert a form feed in the text at this point.\n" +
            "  - \\': Insert a single quote character in the text at this point.\n" +
            "  - \\\": Insert double quote character a in the text at this point.\n" +
            "  - \\\\: Insert a backslash in the text at this point.\n");

    System.out.println("Example");
    System.out.println("She said \"Hello!\" to me.");
  }
}
