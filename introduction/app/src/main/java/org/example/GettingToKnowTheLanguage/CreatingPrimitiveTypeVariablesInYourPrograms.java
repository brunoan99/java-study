package org.example.GettingToKnowTheLanguage;

import org.example.Utils.Article;

public class CreatingPrimitiveTypeVariablesInYourPrograms implements Article {
  public void execute() {
    System.out.printf("Primitive Types:\n" +
        "  - byte: 8bit -> -128 to 127\n" +
        "  - short: 16bit -> -32,768 to 32,767\\n" +
        "  - int: 32bit -> -2,147,483,648 to 2,147,483,647\n" +
        "  - long: 64bit -> -2⁶³ to 2⁶³\n" +
        "  - float: 32bit IEEE 754 floating point\n" +
        "  - double: 64bit IEEE 754 floating point\n" +
        "  - boolean: just two states true and false\n" +
        "  - char: 16bit -> \\u0000 to \\uffff (65,535 values)\n" +
        "  - string: immutable objects that represents texts\n\n");

    System.out.printf("Compiler never assigns a default value to uninitialized local variables.\n\n");

    byte newByte = 0;
    short newShort = 0;
    int newInt = 0;
    long newLong = 0L;
    float newFloat = 0.0f;
    double newDouble = 0.0d;
    boolean newBoolean = false;
    char newChar = '\u0000';
    String newString = null;
    System.out.printf("Default Values for Fields:\n" +
        "  - byte: " + newByte + "\n" +
        "  - short: " + newShort + "\n" +
        "  - int: " + newInt + "\n" +
        "  - long: " + newLong + "\n" +
        "  - float: " + newFloat + "\n" +
        "  - double: " + newDouble + "\n" +
        "  - boolean: " + newBoolean + "\n" +
        "  - char: " + newChar + "\n" +
        "  - string: " + newString + "\n");
  }
}
