package org.example.GettingToKnowTheLanguage.JavaLanguageBasics;

import org.example.Utils.Article;

public class UsingOperatorsInYourPrograms implements Article {
  public void execute() {
    System.out.println("Operators:\n" +
        "  - postfix: expr++ expr--\n" +
        "  - unary: ++expr --expr +expr -expr ~ !\n" +
        "  - multiplicative: * / %\n" +
        "  - additive: + -\n" +
        "  - shift: << >> >>>\n" +
        "  - relational: < > <= >= instanceof\n" +
        "  - equality: == !=\n" +
        "  - bitwise AND: &\n" +
        "  - bitwise exclusive OR: ^\n" +
        "  - bitwise inclusive OR: |\n" +
        "  - logical AND: &&\n" +
        "  - logical OR: ||\n" +
        "  - assignment: = += -= *= /= %= &= ^= |= <<= >>= >>>=\n\n");
  }
}
