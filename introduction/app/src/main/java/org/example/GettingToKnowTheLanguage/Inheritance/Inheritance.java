package org.example.GettingToKnowTheLanguage.Inheritance;

import org.example.Utils.Article;
import org.example.Utils.SubArticle;

public class Inheritance implements Article {
  public void execute() {
    Article.display("Inheritance Example", new InheritanceDemo());

    Article.display("Overriding and Hiding Methods", new OverridingAndHidingMethods());

    Article.display("Polymorphism", new Polymorphism());

    Article.display("Object as a Superclass", new ObjectAsASuperclass());

    Article.display("Abstract Methods and Classes", new AbstractMethodsAndClasses());
  }
}

class InheritanceDemo implements SubArticle {

  class Bicycle {

    // the Bicycle class has three fields
    public int cadence;
    public int gear;
    public int speed;

    // the Bicycle class has one constructor
    public Bicycle(int startCadence, int startSpeed, int startGear) {
      gear = startGear;
      cadence = startCadence;
      speed = startSpeed;
    }

    // the Bicycle class has four methods
    public void setCadence(int newValue) {
      cadence = newValue;
    }

    public void setGear(int newValue) {
      gear = newValue;
    }

    public void applyBrake(int decrement) {
      speed -= decrement;
    }

    public void speedUp(int increment) {
      speed += increment;
    }
  }

  class MountainBike extends Bicycle {

    // the MountainBike subclass adds one field
    public int seatHeight;

    // the MountainBike subclass has one constructor
    public MountainBike(int startHeight,
        int startCadence,
        int startSpeed,
        int startGear) {
      super(startCadence, startSpeed, startGear);
      seatHeight = startHeight;
    }

    // the MountainBike subclass adds one method
    public void setHeight(int newValue) {
      seatHeight = newValue;
    }
  }

  @SuppressWarnings("unused")
  public void execute() {
    Object obj = new MountainBike(1, 10, 0, 1);
    System.out.println("Obj Class: " + obj.getClass());
    if (obj instanceof MountainBike) {
      MountainBike myBike = (MountainBike) obj;
      System.out.println("Obj Class: " + obj.getClass());
    }
  }
}
