package org.example.GettingToKnowTheLanguage.Generics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.example.Utils.SubArticle;

public class RestrictionOnGenerics implements SubArticle {
  public void execute() {
    new CannotCreateInstancesOfTypesParameters().execute();
  }
}

class CannotInstantiateGenericTypesWithPrimitiveTypes {
  class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  public void execute() {
    // Pair<int, char> p = new Pair<>(8, 'a'); // compile-time error
    Pair<Integer, Character> p = new Pair(8, 'a'); // fine
    // Compiler will autobox to
    Pair<Integer, Character> p1 = new Pair(Integer.valueOf(8), Character.valueOf('a'));
  }
}

class CannotCreateInstancesOfTypesParameters {
  // Compile-time error
  // public static <E> void append(List<E> list) {
  // E elem = new E();
  // list.add(elem);
  // }
  public static <E> void append(List<E> list, Class<E> cls) throws Exception {
    E elem = cls.newInstance();
    list.add(elem);
  }

  public void execute() {
    List<String> ls = new ArrayList<>();
    System.out.println("Length Before: " + ls.toArray().length);
    try {
      append(ls, String.class);
    } catch (Exception e) {
    }
    System.out.println("Length After: " + ls.toArray().length);
  }
}

class CannotCreateCatchOrThrowObjectsOfParameterizedTypes {
  // Extends Throwable indirectly
  // class MathException<T> extends Exception { /* ... */ } // compile-time error

  // Extends Throwable directly
  // class QueueFullException<T> extends Throwable { /* ... */ // compile-time
  // error
  class Parser<T extends Exception> {
    public void parse(File file) throws T { // OK
      // ...
    }
  }
}
