package dev.brunoan99.algorithms.compression;

public class RLE {

  private RLE() {
  }

  public static String compress(String input) {
    if (input == null || input.isEmpty()) {
      return "";
    }

    StringBuilder compressed = new StringBuilder();
    int count = 1;

    for (int i = 0; i < input.length(); i++) {
      if (i == input.length() - 1 || input.charAt(i) != input.charAt(i + 1)) {
        compressed.append(count);
        char actualChar = input.charAt(i);
        compressed.append(actualChar);
        count = 1;
      } else {
        count++;
      }
    }

    return compressed.toString();
  }

  public static String decompress(String input) {
    if (input == null || input.isEmpty()) {
      return "";
    }

    StringBuilder decompressed = new StringBuilder();
    int count = 0;

    for (char ch : input.toCharArray()) {
      if (Character.isDigit(ch)) {
        count = count * 10 + Character.getNumericValue(ch);
      } else {
        decompressed.append(String.valueOf(ch).repeat(count));
        count = 0;
      }
    }

    return decompressed.toString();
  }
}
