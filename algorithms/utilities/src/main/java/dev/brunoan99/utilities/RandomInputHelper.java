package dev.brunoan99.utilities;

import java.util.Random;

public class RandomInputHelper {

  public record InputLine(int randomStringLength, int maxSequenceLength, String value) {
  }

  public static InputLine generateLine(int randomStringLength, int maxSequenceLength, Random random) {
    String[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");

    StringBuilder sb = new StringBuilder();
    while (sb.length() < randomStringLength) {
      int remaining = randomStringLength - sb.length();
      int maxSeq = Math.min(maxSequenceLength, remaining);
      int seqLen = (int) (random.nextDouble() * maxSeq) + 1;
      sb.append(characters[(int) (random.nextDouble() * characters.length)].repeat(seqLen));
    }
    return new InputLine(randomStringLength, maxSequenceLength, sb.toString());
  }
}
