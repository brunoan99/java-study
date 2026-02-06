package org.example.GettingToKnowTheLanguage.LambdaExpressions;

import org.example.Utils.Article;

public class LambdaExpressions implements Article {
  public void execute() {
    Article.display("Implementing Lambda Expressions", new IntroducingLambdaExpressions());

    Article.display("Using Lambda Expressions", new UsingLambdaExpressions());

    Article.display("Writing Lambda Expressions as Method References",
        new WritingLambdaExpressionsAsMethodReferences());

    Article.display("Combining Lambda Expressions",
        new CombiningLambdaExpressions());

    Article.display("Writing and Combining Comparators", new WritingAndCombiningComparators());
  }
}
