package org.example.GettingToKnowTheLanguage.JavaLanguageBasics;

import org.example.Utils.Article;

public class JavaLanguageBasics implements Article {

  public void execute() {
    Article.display("Creating Variables and Naming Them", new CreatingVariablesAndNamingThem());

    Article.display("Creating Primitive Type Variables In Your Programs",
        new CreatingPrimitiveTypeVariablesInYourPrograms());

    Article.display("Creating Arrays in Your Programs", new CreatingArraysInYourPrograms());

    Article.display("Using the Var Type Identifier", new UsingTheVarTypeIdentifier());

    Article.display("Using Operators in Your Programs", new UsingOperatorsInYourPrograms());

    Article.display("Control Flow Statements", new ControlFlowStatements());

    Article.display("Braching With Switch Statements and Expressions",
        new BranchingWithSwitchStatementsAndExpressions());
  }
}
