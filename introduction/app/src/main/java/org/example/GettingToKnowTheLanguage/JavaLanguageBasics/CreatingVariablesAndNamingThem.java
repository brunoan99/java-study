package org.example.GettingToKnowTheLanguage.JavaLanguageBasics;

import org.example.Utils.Article;

public class CreatingVariablesAndNamingThem implements Article {
  public void execute() {
    System.out.printf(
        "Kinds of variables:\n" +
            "  Instance Variables(Non-Static Fields): int cadence = 0\n" +
            "  Class Variables (Static Fields): static int numGears = 6\n" +
            "   |                               final int numGears = 6\n" +
            "  Local Variables: int count = 0\n" +
            "  Parameters: public static void main(String[] args): args variable is a parameter to this method\n\n");
    System.out.printf(
        "Naming Variables:\n" +
            "  - begin with letters\n" +
            "  - use camelCase\n" +
            "  - avoid $\n" +
            "  - avoid _\n" +
            "  - white spaces are not permitted\n" +
            "  - avoid abbreviations$\n" +
            "  Examples: gearRatio, currentGear or NUM_GEARS for constant values\n");
  }
}
