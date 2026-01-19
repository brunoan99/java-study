package org.example.GettingToKnowTheLanguage.Inheritance;

import org.example.Utils.SubArticle;

public class OverridingAndHidingMethods implements SubArticle {
  public void execute() {
    SubArticle.display("Overriding Static Methods", new OverridingStaticMethod());

    SubArticle.display("Overriding Interface Methods", new OverridingInterfaceMethod());
  }

}

class OverridingStaticMethod implements SubArticle {
  class Animal {
    public static void testClassMethod() {
      System.out.println("The static method in Animal");
    }

    public void testInstanceMethod() {
      System.out.println("The instance method in Animal");
    }
  }

  class Cat extends Animal {
    public static void testClassMethod() {
      System.out.println("The static method in Cat");
    }

    public void testInstanceMethod() {
      System.out.println("The instance method in Cat");
    }
  }

  public void execute() {
    Cat myCat = new Cat();
    Animal myAnimal = myCat;
    Animal.testClassMethod();
    myAnimal.testInstanceMethod();
  }
}

class OverridingInterfaceMethod implements SubArticle {
  class Horse {
    public String identifyMyself() {
      return "I am a horse.";
    }
  }

  interface Flyer {
    default public String identifyMyself() {
      return "I am able to fly.";
    }
  }

  interface Mythical {
    default public String identifyMyself() {
      return "I am a mythical creature.";
    }
  }

  class Pegasus extends Horse implements Flyer, Mythical {
  }

  interface Animal {
    default public String identifyMyself() {
      return "I am an animal.";
    }
  }

  interface EggLayer extends Animal {
    default public String identifyMyself() {
      return "I am able to lay eggs.";
    }
  }

  interface FireBreather extends Animal {
  }

  class Dragon implements EggLayer, FireBreather {
  }

  public void execute() {
    Pegasus myPegasus = new Pegasus();
    System.out.println(myPegasus.identifyMyself());
    Dragon myDragon = new Dragon();
    System.out.println(myDragon.identifyMyself());
  }
}
