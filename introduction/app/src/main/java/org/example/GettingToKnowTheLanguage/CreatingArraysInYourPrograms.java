package org.example.GettingToKnowTheLanguage;

import org.example.Utils.Article;

public class CreatingArraysInYourPrograms implements Article {
  private static void title(String title) {
    System.out.printf(title + ": \n");
  }

  private static void line() {
    System.out.printf("-  -  -  -  -  -  -  -  -  -  -  \n\n");
  }

  private static void article(String title, Article article) {
    title(title);
    article.execute();
    line();
  }

  public void execute() {
    article("Array Demo", new ArrayDemo());

    article("Multi Dim Array Demo", new MultiDimArrayDemo());

    article("Using the Length of an Array", new UsingLengthDemo());

    article("Copying Arrays", new ArrayCopyDemo());
  }
}

class ArrayDemo implements Article {
  public void execute() {
    // int[] anArray;
    // anArray = new int[10];
    // anArray[0] = 100;
    // anArray[1] = 200;
    // anArray[2] = 300;
    // anArray[3] = 400;
    // anArray[4] = 500;
    // anArray[5] = 600;
    // anArray[6] = 700;
    // anArray[7] = 800;
    // anArray[8] = 900;
    // anArray[9] = 1000;
    int[] anArray = {
        100, 200, 300,
        400, 500, 600,
        700, 800, 900, 1000,
    };

    // System.out.println("Element at index 0: "
    // + anArray[0]);
    // System.out.println("Element at index 1: "
    // + anArray[1]);
    // System.out.println("Element at index 2: "
    // + anArray[2]);
    // System.out.println("Element at index 3: "
    // + anArray[3]);
    // System.out.println("Element at index 4: "
    // + anArray[4]);
    // System.out.println("Element at index 5: "
    // + anArray[5]);
    // System.out.println("Element at index 6: "
    // + anArray[6]);
    // System.out.println("Element at index 7: "
    // + anArray[7]);
    // System.out.println("Element at index 8: "
    // + anArray[8]);
    // System.out.println("Element at index 9: "
    // + anArray[9]);
    for (int index = 0; index < anArray.length; index++) {
      System.out.println("Element at index " + index + ": " + anArray[index]);
    }
  }
}

class MultiDimArrayDemo implements Article {
  public void execute() {
    String[][] names = {
        { "Mr.", "Mrs.", "Ms." },
        { "Smith", "Jones" }
    };
    System.out.println(names[0][0] + " " + names[1][0]);
    System.out.println(names[0][2] + " " + names[1][1]);
  }
}

class UsingLengthDemo implements Article {
  public void execute() {
    String[][] strings = {
        { "one" },
        { "Maria", "Jennifer", "Patricia" },
        { "James", "Michael" },
        { "Washington", "London", "Paris", "Berlin", "Tokyo" }
    };

    for (int arrayIndex = 0; arrayIndex < strings.length; arrayIndex++) {
      for (int index = 0; index < strings[arrayIndex].length; index++) {
        System.out.print(strings[arrayIndex][index] + " ");
      }
      System.out.println();
    }
  }
}

class ArrayCopyDemo implements Article {
  public void execute() {
    String[] copyFrom = {
        "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
        "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",
        "Marocchino", "Ristretto" };
    String[] copyTo = new String[7];
    System.out.println("Starting: ");

    System.out.print("copyFrom: ");
    for (String element : copyFrom) {
      System.out.print(element + " ");
    }
    System.out.println();

    System.out.print("copyTo: ");
    for (String element : copyTo) {
      System.out.print(element + " ");
    }
    System.out.printf("\n\n");

    System.arraycopy(copyFrom, 2, copyTo, 0, 7);
    // String[] copyTo = Arrays.copyOfRange(copyFrom, 2, 9)
    System.out.print("After:               copyTo: ");
    for (String element : copyTo) {
      System.out.print(element + " ");
    }
    System.out.println();

    // Arrays.stream(copyTo).map(element -> element + " ").forEach(IO::print)
    // IO.printl(Arrays.toString(copyTo));
  }
}
