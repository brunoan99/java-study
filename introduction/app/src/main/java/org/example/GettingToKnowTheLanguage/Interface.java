package org.example.GettingToKnowTheLanguage;

public class Interface {
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
