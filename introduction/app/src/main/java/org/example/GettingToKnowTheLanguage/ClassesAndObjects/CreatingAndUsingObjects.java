package org.example.GettingToKnowTheLanguage.ClassesAndObjects;

import org.example.Utils.SubArticle;

public class CreatingAndUsingObjects implements SubArticle {
  public void execute() {
    SubArticle.display("Create Object", new CreateObjectDemo());

    SubArticle.display("Create Student", new CreateStudentDemo());
  }

  class CreateObjectDemo implements SubArticle {
    public void execute() {
      Point originOne = new Point(23, 94);
      Rectangle rectOne = new Rectangle(originOne, 100, 200);
      Rectangle rectTwo = new Rectangle(50, 100);

      System.out.println("Width of rectOne: " + rectOne.width);
      System.out.println("Height of rectOne: " + rectOne.height);
      System.out.println("Area of rectOne: " + rectOne.getArea());

      rectTwo.origin = originOne;

      System.out.println("X Position of rectTwo: " + rectTwo.origin.x);
      System.out.println("Y Position of rectTwo: " + rectTwo.origin.y);

      rectTwo.move(40, 72);
      System.out.println("X Position of rectTwo: " + rectTwo.origin.x);
      System.out.println("Y Position of rectTwo: " + rectTwo.origin.y);
    }
  }

  class CreateStudentDemo implements SubArticle {
    public void execute() {
      Student student1 = new Student(); // Default constructor
      Student student2 = new Student("Alice"); // Name only
      Student student3 = new Student("Bob", 20); // Name and age
      Student student4 = new Student("Carol", 19, "Computer Science");

      System.out.println("=== Student Information ===");
      student1.displayInfo();
      student2.displayInfo();
      student3.displayInfo();
      student4.displayInfo();

      System.out.println("\n=== After Changes ===");
      student2.changeMajor("Mathematics");
      student3.changeMajor("Physics");
    }
  }

  class Point {
    public int x = 0;
    public int y = 0;

    // a constructor!
    public Point(int a, int b) {
      x = a;
      y = b;
    }
  }

  class Rectangle {
    public int width = 0;
    public int height = 0;
    public Point origin;

    public Rectangle() {
      origin = new Point(0, 0);
    }

    public Rectangle(Point p) {
      origin = p;
    }

    public Rectangle(int w, int h) {
      origin = new Point(0, 0);
      width = w;
      height = h;
    }

    public Rectangle(Point p, int w, int h) {
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
  }

  class Student {
    String name;
    int age;
    String major;

    public Student() {
      name = "Unknown";
      age = 0;
      major = "Undeclared";
    }

    public Student(String studentName) {
      name = studentName;
      age = 18;
      major = "Undeclared";
    }

    public Student(String studentName, int studentAge) {
      name = studentName;
      age = studentAge;
      major = "Undeclared";
    }

    public Student(String studentName, int studentAge, String studentMajor) {
      name = studentName;
      age = studentAge;
      major = studentMajor;
    }

    public void displayInfo() {
      System.out.println("Name: " + name + ", Age: " + age + ", Major: " + major);
    }

    public void changeMajor(String newMajor) {
      major = newMajor;
      System.out.println(name + " changed major to " + newMajor);
    }
  }
}
