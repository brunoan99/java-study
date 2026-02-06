package org.example.GettingToKnowTheLanguage.ClassesAndObjects;

import org.example.Utils.SubArticle;

public class NestedClasses implements SubArticle {
  public void execute() {
    SubArticle.display("Nested Classes", new OuterClass());

    SubArticle.display("Shadowing", new ShadowTest());

    SubArticle.display("DataStructure Example Using Inner Class", new DataStructure());

    SubArticle.display("Declaring Local Classes", new LocalClassExample());

    SubArticle.display("Anonymous Classes", new HelloWorldAnonymousClasses());
  }

}

class OuterClass implements SubArticle {

  String outerField = "Outer field";
  static String staticOuterField = "Static outer field";

  class InnerClass {
    void accessMembers() {
      System.out.println(outerField);
      System.out.println(staticOuterField);
    }
  }

  static class StaticNestedClass {
    void accessMembers(OuterClass outer) {
      // Compiler error: Cannot make a static reference to the non-static
      // field outerField
      // System.out.println(outerField);
      System.out.println(outer.outerField);
      System.out.println(staticOuterField);
    }
  }

  public void execute() {
    System.out.println("Inner class:");
    System.out.println("------------");
    OuterClass outerObject = new OuterClass();
    OuterClass.InnerClass innerObject = outerObject.new InnerClass();
    innerObject.accessMembers();

    System.out.println("\nStatic nested class:");
    System.out.println("--------------------");
    StaticNestedClass staticNestedObject = new StaticNestedClass();
    staticNestedObject.accessMembers(outerObject);

    System.out.println("\nTop-level class:");
    System.out.println("--------------------");
    TopLevelClass topLevelObject = new TopLevelClass();
    topLevelObject.accessMembers(outerObject);
  }
}

class TopLevelClass {

  void accessMembers(OuterClass outer) {
    // Compiler error: Cannot make a static reference to the non-static
    // field OuterClass.outerField
    // System.out.println(OuterClass.outerField);
    System.out.println(outer.outerField);
    System.out.println(OuterClass.staticOuterField);
  }
}

class ShadowTest implements SubArticle {

  public int x = 0;

  class FirstLevel {

    public int x = 1;

    void methodInFirstLevel(int x) {
      System.out.println("x = " + x);
      System.out.println("this.x = " + this.x);
      System.out.println("ShadowTest.this.x = " + ShadowTest.this.x);
    }
  }

  public void execute() {
    ShadowTest st = new ShadowTest();
    ShadowTest.FirstLevel fl = st.new FirstLevel();
    fl.methodInFirstLevel(23);
  }
}

class DataStructure implements SubArticle {

  // Create an array
  private final static int SIZE = 15;
  private int[] arrayOfInts = new int[SIZE];

  public DataStructure() {
    // fill the array with ascending integer values
    for (int i = 0; i < SIZE; i++) {
      arrayOfInts[i] = i;
    }
  }

  public void printEven() {

    // Print out values of even indices of the array
    DataStructureIterator iterator = this.new EvenIterator();
    while (iterator.hasNext()) {
      System.out.print(iterator.next() + " ");
    }
    System.out.println();
  }

  interface DataStructureIterator extends java.util.Iterator<Integer> {
  }

  // Inner class implements the DataStructureIterator interface,
  // which extends the Iterator<Integer> interface

  private class EvenIterator implements DataStructureIterator {

    // Start stepping through the array from the beginning
    private int nextIndex = 0;

    public boolean hasNext() {

      // Check if the current element is the last in the array
      return (nextIndex <= SIZE - 1);
    }

    public Integer next() {

      // Record a value of an even index of the array
      Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

      // Get the next even element
      nextIndex += 2;
      return retValue;
    }
  }

  public void execute() {

    // Fill the array with integer values and print out only
    // values of even indices
    DataStructure ds = new DataStructure();
    ds.printEven();
  }
}

class LocalClassExample implements SubArticle {
  static String regularExpression = "[^0-9]";

  public static void validatePhoneNumber(
      String phoneNumber1, String phoneNumber2) {

    final int numberLength = 10;

    class PhoneNumber {

      String formattedPhoneNumber = null;

      PhoneNumber(String phoneNumber) {
        // numberLength = 7;
        String currentNumber = phoneNumber.replaceAll(
            regularExpression, "");
        if (currentNumber.length() == numberLength)
          formattedPhoneNumber = currentNumber;
        else
          formattedPhoneNumber = null;
      }

      public String getNumber() {
        return formattedPhoneNumber;
      }
    }

    PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
    PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);

    if (myNumber1.getNumber() == null)
      System.out.println("First number is invalid");
    else
      System.out.println("First number is " + myNumber1.getNumber());
    if (myNumber2.getNumber() == null)
      System.out.println("Second number is invalid");
    else
      System.out.println("Second number is " + myNumber2.getNumber());

  }

  public void execute() {
    validatePhoneNumber("123-456-7890", "456-7890");
  }
}

class HelloWorldAnonymousClasses implements SubArticle {
  interface HelloWorld {
    public void greet();

    public void greetSomeone(String someone);
  }

  public void sayHello() {

    class EnglishGreeting implements HelloWorld {
      String name = "world";

      public void greet() {
        greetSomeone("world");
      }

      public void greetSomeone(String someone) {
        name = someone;
        System.out.println("Hello " + name);
      }
    }

    HelloWorld englishGreeting = new EnglishGreeting();

    HelloWorld frenchGreeting = new HelloWorld() {
      String name = "tout le monde";

      public void greet() {
        greetSomeone("tout le monde");
      }

      public void greetSomeone(String someone) {
        name = someone;
        System.out.println("Salut " + name);
      }
    };

    HelloWorld spanishGreeting = new HelloWorld() {
      String name = "mundo";

      public void greet() {
        greetSomeone("mundo");
      }

      public void greetSomeone(String someone) {
        name = someone;
        System.out.println("Hola, " + name);
      }
    };
    englishGreeting.greet();
    frenchGreeting.greetSomeone("Fred");
    spanishGreeting.greet();
  }

  public void execute() {
    HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();
    myApp.sayHello();
  }

}
