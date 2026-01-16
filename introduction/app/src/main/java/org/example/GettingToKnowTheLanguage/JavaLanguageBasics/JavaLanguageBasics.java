package org.example.GettingToKnowTheLanguage.JavaLanguageBasics;

import org.example.Utils.Article;

public class JavaLanguageBasics implements Article {
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
    article("Creating Variables and Naming Them", new CreatingVariablesAndNamingThem());

    article("Creating Primitive Type Variables In Your Programs", new CreatingPrimitiveTypeVariablesInYourPrograms());

    article("Creating Arrays in Your Programs", new CreatingArraysInYourPrograms());

    article("Using the Var Type Identifier", new UsingTheVarTypeIdentifier());

    article("Using Operators in Your Programs", new UsingOperatorsInYourPrograms());

    article("Control Flow Statements", new ControlFlowStatements());

    article("Braching With Switch Statements and Expressions", new BranchingWithSwitchStatementsAndExpressions());
  }
}
