package org.example.GettingToKnowTheLanguage.ClassesAndObjects;

import org.example.Utils.SubArticle;

public class CreatingClasses implements SubArticle {
  public void execute() {
  }
}

class Bicycle {
  int cadence;
  int gear;
  int speed;

  Bicycle(int startCadence, int startSpeed, int startGear) {
    cadence = startCadence;
    gear = startGear;
    speed = startSpeed;
  }

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
  public int seatHeight;

  public MountainBike(int startHeight, int startCadence, int startSpeed, int startGear) {
    super(startCadence, startGear, startSpeed);
    seatHeight = startHeight;
  }

  void setHeight(int newValue) {
    seatHeight = newValue;
  }
}

// class MyClass extends MySuperClass implements MyInterface {}
