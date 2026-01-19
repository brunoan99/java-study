package org.example.GettingToKnowTheLanguage.NumbersAndStrings;

import org.example.Utils.SubArticle;

public class Strings implements SubArticle {
  public void execute() {
    SubArticle.display("String Palindrome Demo", new StringDemo());

    SubArticle.display("Converting Strings to Numbers", new StringToNumberDemo());

    SubArticle.display("Converting Numbers to Strings", new NumberToStringDemo());

    SubArticle.display("The String Class in Action", new FilenameDemo());

    SubArticle.display("Comparing Strings and Portions of Strings", new RegionMatchesDemo());
  }
}

class StringDemo implements SubArticle {
  public void execute() {
    String palindrome = "Dot saw I was Tod";
    int len = palindrome.length();
    char[] tempCharArray = new char[len];
    char[] charArray = new char[len];

    // put original string in an
    // array of chars
    for (int i = 0; i < len; i++) {
      tempCharArray[i] = palindrome.charAt(i);
    }

    // reverse array of chars
    for (int j = 0; j < len; j++) {
      charArray[j] = tempCharArray[len - 1 - j];
    }

    String reversePalindrome = new String(charArray);
    System.out.println(reversePalindrome);
  }
}

class StringToNumberDemo implements SubArticle {
  public void execute() {

    String aString = "4.5";
    String bString = "87.2";
    float a = Float.valueOf(aString).floatValue();
    float b = Float.valueOf(bString).floatValue();
    System.out.println("a + b = " +
        (a + b));
    System.out.println("a - b = " +
        (a - b));
    System.out.println("a * b = " +
        (a * b));
    System.out.println("a / b = " +
        (a / b));
    System.out.println("a % b = " +
        (a % b));

  }
}

class NumberToStringDemo implements SubArticle {

  public void execute() {
    double d = 858.48;
    String s = Double.toString(d);

    int dot = s.indexOf('.');

    System.out.println(dot + " digits " +
        "before decimal point.");
    System.out.println((s.length() - dot - 1) +
        " digits after decimal point.");
  }
}

class Filename {
  private String fullPath;
  private char pathSeparator,
      extensionSeparator;

  public Filename(String str, char sep, char ext) {
    fullPath = str;
    pathSeparator = sep;
    extensionSeparator = ext;
  }

  public String extension() {
    int dot = fullPath.lastIndexOf(extensionSeparator);
    return fullPath.substring(dot + 1);
  }

  // gets filename without extension
  public String filename() {
    int dot = fullPath.lastIndexOf(extensionSeparator);
    int sep = fullPath.lastIndexOf(pathSeparator);
    return fullPath.substring(sep + 1, dot);
  }

  public String path() {
    int sep = fullPath.lastIndexOf(pathSeparator);
    return fullPath.substring(0, sep);
  }
}

class FilenameDemo implements SubArticle {
  public void execute() {
    final String FPATH = "/home/user/index.html";
    Filename myHomePage = new Filename(FPATH, '/', '.');
    System.out.println("Extension = " + myHomePage.extension());
    System.out.println("Filename = " + myHomePage.filename());
    System.out.println("Path = " + myHomePage.path());
  }
}

class RegionMatchesDemo implements SubArticle {
  public void execute() {
    String searchMe = "Green Eggs and Ham";
    String findMe = "Eggs";
    int searchMeLength = searchMe.length();
    int findMeLength = findMe.length();
    boolean foundIt = false;
    for (int i = 0; i <= (searchMeLength - findMeLength); i++) {
      if (searchMe.regionMatches(i, findMe, 0, findMeLength)) {
        foundIt = true;
        System.out.println(searchMe.substring(i, i + findMeLength));
        break;
      }
    }
    if (!foundIt)
      System.out.println("No match found.");
  }
}
