package org.example.MasteringTheAPI.TheCollectionsFramework;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.example.Utils.Article;
import org.example.Utils.SubArticle;

public class IteratingOverTheElementsOfACollection implements SubArticle {
  public void execute() {

    Article.display("Using an Iterator on a Collection", new UsingAnIteratorOnACollection());

    Article.display("Implementing the Iterable Interface", new ImplementingTheIterableInterface());

  }
}

class UsingAnIteratorOnACollection implements SubArticle {
  public void execute() {
    Collection<String> strings = List.of("one", "two", "three", "four");
    for (Iterator<String> iterator = strings.iterator(); iterator.hasNext();) {
      String element = iterator.next();
      if (element.length() == 3) {
        IO.println(element);
      }
    }
  }
}

class ImplementingTheIterableInterface implements SubArticle {

  class Range implements Iterable<Integer> {

    private final int start;
    private final int end;

    public Range(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
      return new Iterator<>() {
        private int index = start;

        @Override
        public boolean hasNext() {
          return index < end;
        }

        @Override
        public Integer next() {
          if (index > end) {
            throw new NoSuchElementException("" + index);
          }
          int currentIndex = index;
          index++;
          return currentIndex;
        }
      };
    }
  }

  public void execute() {
    for (int i : new Range(0, 5)) {
      IO.println("i = " + i);
    }
  }
}
