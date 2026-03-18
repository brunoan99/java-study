package dev.brunoan99.algorithms.compression;

import java.util.ArrayList;
import java.util.List;

public class LZ77 {

  private static final int DEFAULT_WINDOW_SIZE = 4096;
  private static final int DEFAULT_LOOKAHEAD_BUFFER_SIZE = 16;
  private static final char END_OF_STREAM = '\u0000';

  private LZ77() {
  }

  public record Token(int offset, int length, char nextChar) {
    int getSize() {
      int separatorsSize = 1;
      int commasSize = 3;
      int offsetSize = String.valueOf(offset).length();
      int lengthSize = String.valueOf(length).length();
      int charSize = 1;
      return separatorsSize + commasSize + offsetSize + lengthSize + charSize;
    }
  }

  public static List<Token> compress(String input, int windowSize, int lookaheadBufferSize) {
    if (input == null)
      return new ArrayList<>();

    if (windowSize <= 0 || lookaheadBufferSize <= 0)
      throw new IllegalArgumentException("WindowsSize and LockAheadBufferSize must be positive");

    List<Token> tokens = new ArrayList<>();
    int currentPosition = 0;

    while (currentPosition < input.length()) {
      int bestMatchDistance = 0;
      int bestMatchLength = 0;

      int searchBufferStart = Math.max(0, currentPosition - windowSize);
      int lookaheadEnd = Math.min(currentPosition + lookaheadBufferSize, input.length());

      for (int i = searchBufferStart; i < currentPosition; i++) {
        int currentMatchLength = 0;

        while (currentPosition + currentMatchLength < lookaheadEnd) {
          int sourceIndex = i + currentMatchLength;

          if (sourceIndex >= currentPosition) {
            int offset = currentPosition - i;
            sourceIndex = i + (currentMatchLength % offset);
          }

          if (input.charAt(sourceIndex) == input.charAt(currentPosition + currentMatchLength)) {
            currentMatchLength++;
          } else {
            break;
          }
        }

        if (currentMatchLength > bestMatchLength) {
          bestMatchLength = currentMatchLength;
          bestMatchDistance = currentPosition - i;
        }
      }

      char nextChar;
      if (currentPosition + bestMatchLength < input.length()) {
        nextChar = input.charAt(currentPosition + bestMatchLength);
      } else {
        nextChar = END_OF_STREAM;
      }

      tokens.add(new Token(bestMatchDistance, bestMatchLength, nextChar));

      if (nextChar == END_OF_STREAM) {
        currentPosition += bestMatchLength;
      } else {
        currentPosition += bestMatchLength + 1;
      }
    }

    return tokens;

  }

  public static List<Token> compress(String input) {
    return compress(input, DEFAULT_WINDOW_SIZE, DEFAULT_LOOKAHEAD_BUFFER_SIZE);
  }

  public static String decompress(List<Token> input) {
    if (input == null)
      return "";

    StringBuilder sb = new StringBuilder();

    for (Token token : input) {
      if (token.length > 0) {
        int startIndex = sb.length() - token.offset;

        for (int i = 0; i < token.length; i++) {
          sb.append(sb.charAt(startIndex + i));
        }
      }

      if (token.nextChar != END_OF_STREAM) {
        sb.append(token.nextChar);
      }
    }

    return sb.toString();
  }

}
