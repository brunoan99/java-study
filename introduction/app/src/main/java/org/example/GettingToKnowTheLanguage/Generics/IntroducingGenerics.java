package org.example.GettingToKnowTheLanguage.Generics;

import org.example.Utils.SubArticle;

public class IntroducingGenerics implements SubArticle {
  public void execute() {
    new GenericsDemo().execute();
  }
}

class Box<T> {
  private T t;

  public void set(T t) {
    this.t = t;
  }

  public T get() {
    return t;
  };
}

class BoxDemo {
  public static <U> void addBox(U u, java.util.List<Box<U>> boxes) {
    Box<U> box = new Box<>();
    box.set(u);
    boxes.add(box);
  }

  public static <U> void outputBoxes(java.util.List<Box<U>> boxes) {
    int counter = 0;
    for (Box<U> box : boxes) {
      U boxContents = box.get();
      System.out.println("Box #" + counter + " contains [" +
          boxContents.toString() + "]");
      counter++;
    }
  }
}

class GenericsDemo implements SubArticle {

  interface Pair<K, V> {
    public K getKey();

    public V getValue();
  }

  class OrderedPair<K, V> implements Pair<K, V> {
    private K key;
    private V value;

    public OrderedPair(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public void setKey(K key) {
      this.key = key;
    }

    public void setValue(V value) {
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }
  }

  class Util {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
      return p1.getKey().equals(p2.getKey()) &&
          p1.getValue().equals(p2.getValue());
    }
  }

  class NaturalNumber<T extends Integer> {

    private T n;

    public NaturalNumber(T n) {
      this.n = n;
    }

    public boolean isEven() {
      return n.intValue() % 2 == 0;
    }
  }

  public void execute() {
    Box<Number> integerBox = new Box<>();
    integerBox.set(1);
    System.out.println("IntegerBox: " + integerBox);
    System.out.println("IntegerBox Value: " + integerBox.get());
    System.out.println("IntegerBox Class: " + integerBox.getClass());
    System.out.println();

    Pair<String, Integer> p1 = new OrderedPair<String, Integer>("Even", 8);
    Pair<String, String> p2 = new OrderedPair<String, String>("hello", "world");
    System.out.println("P1: " + p1);
    System.out.println("P1 Key: " + p1.getKey());
    System.out.println("P1 Value: " + p1.getValue());
    System.out.println("P1 Class: " + p1.getClass());
    System.out.println("P2: " + p2);
    System.out.println("P2 Key: " + p2.getKey());
    System.out.println("P2 Value: " + p2.getValue());
    System.out.println("P2 Class: " + p2.getClass());
    System.out.println();

    OrderedPair<Integer, String> p3 = new OrderedPair<>(1, "apple");
    OrderedPair<Integer, String> p4 = new OrderedPair<>(2, "pear");
    boolean same = Util.<Integer, String>compare(p3, p4);
    System.out.println("P3 Key: " + p3.getKey() + ", Value: " + p3.getValue());
    System.out.println("P4 Key: " + p4.getKey() + ", Value: " + p4.getValue());
    System.out.println("P3 Same as P4: " + same);
    System.out.println();

    java.util.ArrayList<Box<Integer>> listOfIntegerBoxes = new java.util.ArrayList<>();
    BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes);
    BoxDemo.addBox(Integer.valueOf(20), listOfIntegerBoxes);
    BoxDemo.addBox(Integer.valueOf(30), listOfIntegerBoxes);
    BoxDemo.outputBoxes(listOfIntegerBoxes);
  }
}
