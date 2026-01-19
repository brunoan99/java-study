package org.example.GettingToKnowTheLanguage.Inheritance;

import org.example.Utils.SubArticle;

public class Polymorphism implements SubArticle {
  public void execute() {
    new PolymorphismDemo().execute();
  }
}

class PolymorphismDemo implements SubArticle {
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

    public void printDescription() {
      System.out.println("\nBike is " + "in gear " + this.gear
          + " with a cadence of " + this.cadence +
          " and travelling at a speed of " + this.speed + ". ");
    }
  }

  class MountainBike extends Bicycle {
    private String suspension;

    public MountainBike(
        int startCadence,
        int startSpeed,
        int startGear,
        String suspensionType) {
      super(startCadence,
          startSpeed,
          startGear);
      this.setSuspension(suspensionType);
    }

    public String getSuspension() {
      return this.suspension;
    }

    public void setSuspension(String suspensionType) {
      this.suspension = suspensionType;
    }

    public void printDescription() {
      super.printDescription();
      System.out.println("The " + "MountainBike has a " +
          getSuspension() + " suspension.");
    }
  }

  class RoadBike extends Bicycle {
    // In millimeters (mm)
    private int tireWidth;

    public RoadBike(int startCadence,
        int startSpeed,
        int startGear,
        int newTireWidth) {
      super(startCadence,
          startSpeed,
          startGear);
      this.setTireWidth(newTireWidth);
    }

    public int getTireWidth() {
      return this.tireWidth;
    }

    public void setTireWidth(int newTireWidth) {
      this.tireWidth = newTireWidth;
    }

    public void printDescription() {
      super.printDescription();
      System.out.println("The RoadBike" + " has " + getTireWidth() +
          " MM tires.");
    }
  }

  public void execute() {
    Bicycle bike01, bike02, bike03;

    bike01 = new Bicycle(20, 10, 1);
    bike02 = new MountainBike(20, 10, 5, "Dual");
    bike03 = new RoadBike(40, 20, 8, 23);

    bike01.printDescription();
    bike02.printDescription();
    bike03.printDescription();

  }
}
