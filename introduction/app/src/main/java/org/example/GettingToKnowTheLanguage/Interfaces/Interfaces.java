package org.example.GettingToKnowTheLanguage.Interfaces;

import java.time.*;

import org.example.Utils.Article;
import org.example.Utils.SubArticle;

public class Interfaces implements Article {
  public void execute() {
    Article.display("Implementing Interface", new InterfaceDemo());

    Article.display("Default Methods", new DefaultMethods());
  }
}

class InterfaceDemo implements SubArticle {
  interface Relatable {
    public int isLargerThan(Relatable other);
  }

  class Point {
    public int x;
    public int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  class RectanglePlus implements Relatable {
    public int width = 0;
    public int height = 0;
    public Point origin;

    public RectanglePlus() {
      origin = new Point(0, 0);
    }

    public RectanglePlus(Point p) {
      origin = p;
    }

    public RectanglePlus(int w, int h) {
      origin = new Point(0, 0);
      width = w;
      height = h;
    }

    public RectanglePlus(Point p, int w, int h) {
      origin = p;
      width = w;
      height = h;
    }

    public void move(int x, int y) {
      origin.x = x;
      origin.y = y;
    }

    public int getArea() {
      return width * height;
    }

    public int isLargerThan(Relatable other) {
      RectanglePlus otherRect = (RectanglePlus) other;
      if (this.getArea() < otherRect.getArea())
        return -1;
      else if (this.getArea() > otherRect.getArea())
        return 1;
      else
        return 0;
    }
  }

  public void execute() {
    RectanglePlus r1 = new RectanglePlus(new Point(1, 3), 10, 20);
    RectanglePlus r2 = new RectanglePlus(15, 25);

    int isLarger = r1.isLargerThan(r2);
    System.out.println("R1 is larger than R2? " + isLarger);
  }
}

class DefaultMethods implements SubArticle {

  interface TimeClient {
    void setTime(int hour, int minute, int second);

    void setDate(int day, int month, int year);

    void setDateAndTime(int day, int month, int year,
        int hour, int minute, int second);

    LocalDateTime getLocalDateTime();

    static ZoneId getZoneId(String zoneString) {
      try {
        return ZoneId.of(zoneString);
      } catch (DateTimeException e) {
        System.err.println("Invalid time zone: " + zoneString +
            "; using default time zone instead.");
        return ZoneId.systemDefault();
      }
    }

    default ZonedDateTime getZonedDateTime(String zoneString) {
      return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
    }
  }

  class SimpleTimeClient implements TimeClient {

    private LocalDateTime dateAndTime;

    public SimpleTimeClient() {
      dateAndTime = LocalDateTime.now();
    }

    public void setTime(int hour, int minute, int second) {
      LocalDate currentDate = LocalDate.from(dateAndTime);
      LocalTime timeToSet = LocalTime.of(hour, minute, second);
      dateAndTime = LocalDateTime.of(currentDate, timeToSet);
    }

    public void setDate(int day, int month, int year) {
      LocalDate dateToSet = LocalDate.of(day, month, year);
      LocalTime currentTime = LocalTime.from(dateAndTime);
      dateAndTime = LocalDateTime.of(dateToSet, currentTime);
    }

    public void setDateAndTime(int day, int month, int year,
        int hour, int minute, int second) {
      LocalDate dateToSet = LocalDate.of(day, month, year);
      LocalTime timeToSet = LocalTime.of(hour, minute, second);
      dateAndTime = LocalDateTime.of(dateToSet, timeToSet);
    }

    public LocalDateTime getLocalDateTime() {
      return dateAndTime;
    }

    public String toString() {
      return dateAndTime.toString();
    }
  }

  public void execute() {
    TimeClient myTimeClient = new SimpleTimeClient();
    System.out.println(myTimeClient.toString());
  }
}

class ExtendingInterfacesWithDefaultMethods implements SubArticle {
  interface TimeClient {
    void setTime(int hour, int minute, int second);

    void setDate(int day, int month, int year);

    void setDateAndTime(int day, int month, int year,
        int hour, int minute, int second);

    LocalDateTime getLocalDateTime();

    static ZoneId getZoneId(String zoneString) {
      try {
        return ZoneId.of(zoneString);
      } catch (DateTimeException e) {
        System.err.println("Invalid time zone: " + zoneString +
            "; using default time zone instead.");
        return ZoneId.systemDefault();
      }
    }

    default ZonedDateTime getZonedDateTime(String zoneString) {
      return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
    }
  }

  interface AnotherTimeClient extends TimeClient {
  }

  interface AbstractZoneTimeClient extends TimeClient {
    public ZonedDateTime getZonedDateTime(String zoneString);
  }

  interface HandleInvalidTimeZoneClient extends TimeClient {
    default public ZonedDateTime getZonedDateTime(String zoneString) {
      try {
        return ZonedDateTime.of(getLocalDateTime(), ZoneId.of(zoneString));
      } catch (DateTimeException e) {
        System.err.println("Invalid zone ID: " + zoneString +
            "; using the default time zone instead.");
        return ZonedDateTime.of(getLocalDateTime(), ZoneId.systemDefault());
      }
    }
  }

  public void execute() {

  }
}
