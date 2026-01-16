package org.example.GettingToKnowTheLanguage.ObjectsClassesInterfacesPackagesAndInheritance;

import org.example.Utils.Article;

public class ObjectsClassesInterfacesPackagesAndInheritance implements Article {
  private static void title(String title) {
    System.out.printf(title + ": \n");
  }

  private static void line() {
    System.out.printf("- - - - - - - - - - - - - - - - -\n\n\n");
  }

  public void execute() {
    title("Class");
    Class.execute();
    line();

    title("Inheritance");
    Inheritance.execute();
    line();

    title("Interface");
    Interface.execute();
    line();
  }

}

class Class {
  public static void execute() {
    Bicycle bike1 = new Bicycle();
    Bicycle bike2 = new Bicycle();

    bike1.changeCadence(50);
    bike1.speedUp(10);
    bike1.changeGear(2);
    bike1.printStates();

    bike2.changeCadence(50);
    bike2.speedUp(10);
    bike2.changeGear(2);
    bike2.changeCadence(40);
    bike2.speedUp(10);
    bike2.changeGear(3);
    bike2.printStates();
  }
}

class Bicycle {
  int cadence = 0;
  int speed = 0;
  int gear = 1;

  void changeCadence(int newValue) {
    cadence = newValue;
  }

  void changeGear(int newValue) {
    gear = newValue;
  }

  void speedUp(int increment) {
    speed += increment;
    // also can be writen as speed = speed + increment
  }

  void applyBreaks(int decrement) {
    speed -= decrement;
    // also can be writen as speed = speed + increment
  }

  void printStates() {
    System.out.println("cadence: " +
        cadence + ", speed: " +
        speed + ", gear: " + gear);
  }
}

class Inheritance {
  public static void execute() {
  }
}

class MountainBike extends Bicycle {

}

class Interface {
  public static void execute() {
    ACMEBicycle bike1 = new ACMEBicycle();

    bike1.changeCadence(50);
    bike1.speedUp(10);
    bike1.changeGear(2);
    bike1.printStates();
  }
}

interface BicycleInterface {
  // wheel revolutions per minute
  void changeCadence(int newValue);

  void changeGear(int newValue);

  void speedUp(int increment);

  void applyBrakes(int decrement);
}

class ACMEBicycle implements BicycleInterface {
  int cadence = 0;
  int speed = 0;
  int gear = 1;

  public void changeCadence(int newValue) {
    cadence = newValue;
  }

  public void changeGear(int newValue) {
    gear = newValue;
  }

  public void speedUp(int increment) {
    speed = speed + increment;
  }

  public void applyBrakes(int decrement) {
    speed = speed - decrement;
  }

  public void printStates() {
    System.out.println("cadence: " +
        cadence + ", speed: " +
        speed + ", gear: " + gear);
  }
}
