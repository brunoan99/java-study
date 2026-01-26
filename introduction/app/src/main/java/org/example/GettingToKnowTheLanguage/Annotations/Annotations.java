package org.example.GettingToKnowTheLanguage.Annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;

import org.example.Utils.Article;

public class Annotations implements Article {
  public void execute() {
  }
}

@Documented
@interface ClassPreamble {
  String author();

  String date();

  int currentRevision()

  default 1;

  String lastModified()

  default "N/A";

  String lastModifiedBy()

  default "N/A";

  String[] reviewers();
}

@ClassPreamble(author = "John Doe", date = "3/17/2002", currentRevision = 6, lastModified = "4/12/2004", lastModifiedBy = "Jane Doe", reviewers = {
    "Alice", "Bob", "Cindy" })
class Generation3List {
}

@interface Schedules {
  Schedule[] value();
}

@Repeatable(Schedules.class)
@interface Schedule {
  String dayOfMonth()

  default "first";

  String dayOfWeek()

  default "Mon";

  int hour() default 12;
}
