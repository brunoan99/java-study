package org.example.MasteringTheAPI.TheCollectionsFramework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.example.Utils.SubArticle;

public class ChoosingImmutableTypesForYourKey implements SubArticle {
  public void execute() {
    IO.println("Avoiding the Use of Mutable Keys");
    Key one = new Key("1");
    Key two = new Key("2");

    Map<Key, String> map = new HashMap<>();
    map.put(one, "one");
    map.put(two, "two");

    IO.println("map.get(one) = " + map.get(one));
    IO.println("map.get(two) = " + map.get(two));

    IO.println();
    one.setKey("5");

    IO.println("map.get(one) = " + map.get(one));
    IO.println("map.get(two) = " + map.get(two));
    IO.println("map.get(new Key(1)) = " + map.get(new Key("1")));
    IO.println("map.get(new Key(2)) = " + map.get(new Key("2")));
    IO.println("map.get(new Key(5)) = " + map.get(new Key("5")));

    IO.println();
    one.setKey("2");

    IO.println("map.get(one) = " + map.get(one));
    IO.println("map.get(two) = " + map.get(two));
    IO.println("map.get(new Key(1)) = " + map.get(new Key("1")));
    IO.println("map.get(new Key(2)) = " + map.get(new Key("2")));

    IO.println("\nDiving in the Structure of HashSet");
    Key one1 = new Key("1");
    Key two1 = new Key("2");
    Set<Key> set = new HashSet<>();
    set.add(one1);
    set.add(two1);

    IO.println("set = " + set);

    // You should never mutate an object once it has been added to a Set!
    one1.setKey("3");
    IO.println("set.contains(one) = " + set.contains(one1));
    boolean addedOne = set.add(one1);
    IO.println("addedOne = " + addedOne);
    IO.println("set = " + set);

    IO.println();
    List<Key> list = new ArrayList<>(set);
    Key key0 = list.get(0);
    Key key2 = list.get(2);

    IO.println("key0 = " + key0);
    IO.println("key2 = " + key2);
    IO.println("key0 == key2 ? " + (key0 == key2));
  }
}

//
// !!!!! This an example of an antipattern !!!!!!
// !!! do not do this in your production code !!!
//
class Key {
  private String key;

  public Key(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  @Override
  public String toString() {
    return key;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Key key = (Key) o;
    return Objects.equals(this.key, key.key);
  }

  @Override
  public int hashCode() {
    return key.hashCode();
  }
}
