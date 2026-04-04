package dev.brunoan99.algorithms.compression;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArithmeticCoding {
  private final Map<Character, Range> symbolMap;
  private final Character EOF = '\0';

  public ArithmeticCoding(String input) throws IllegalArgumentException {
    if (input == null || input.isEmpty())
      throw new IllegalArgumentException("Input cannot be null or empty");
    input = new StringBuilder().append(input).append(EOF).toString();
    this.symbolMap = calculateProbabilitiesMap(input);
  }

  public record Range(BigDecimal low, BigDecimal high) {
  }

  public Map<Character, Range> calculateProbabilitiesMap(String input) {
    Map<Character, Integer> frequencies = new HashMap<>();
    for (char character : input.toCharArray()) {
      frequencies.put(character, frequencies.getOrDefault(character, 0) + 1);
    }

    List<Character> sortedKeys = new ArrayList<>(frequencies.keySet());
    Collections.sort(sortedKeys);

    Map<Character, Range> probabilities = new HashMap<>();
    BigDecimal currentLow = BigDecimal.ZERO;
    int total = input.length();

    for (char character : sortedKeys) {
      BigDecimal characterProbability = BigDecimal.valueOf(frequencies.get(character)).divide(BigDecimal.valueOf(total),
          MathContext.DECIMAL128);
      BigDecimal high = currentLow.add(characterProbability);
      probabilities.put(character, new Range(currentLow, high));
      currentLow = high;
    }

    return probabilities;
  }

  public BigDecimal compress(String input) throws IllegalArgumentException {
    if (input == null || input.isEmpty())
      throw new IllegalArgumentException("Input cannot be null or empty");
    input = new StringBuilder().append(input).append(EOF).toString();

    BigDecimal low = BigDecimal.ZERO;
    BigDecimal high = BigDecimal.ONE;

    for (char symbol : input.toCharArray()) {
      BigDecimal range = high.subtract(low);
      Range symbolRange = symbolMap.get(symbol);

      high = low.add(range.multiply(symbolRange.high()));
      low = low.add(range.multiply(symbolRange.low()));
    }

    return low;
  }

  public String decompress(BigDecimal input) throws IllegalArgumentException {
    if (input == null)
      throw new IllegalArgumentException("Input cannot be null");

    StringBuilder sb = new StringBuilder();

    List<Map.Entry<Character, Range>> sortedSymbols = new ArrayList<>(symbolMap.entrySet());
    sortedSymbols.sort(Map.Entry.comparingByKey());

    BigDecimal low = BigDecimal.ZERO;
    BigDecimal high = BigDecimal.ONE;

    outer: while (true) {
      BigDecimal range = high.subtract(low);

      inner: for (Map.Entry<Character, Range> entry : sortedSymbols) {
        Range symbolRange = entry.getValue();

        BigDecimal symbolLow = low.add(range.multiply(symbolRange.low()));
        BigDecimal symbolHigh = low.add(range.multiply(symbolRange.high()));

        if (input.compareTo(symbolLow) >= 0 && input.compareTo(symbolHigh) < 0) {
          Character nextCharacter = entry.getKey();
          if (nextCharacter == EOF) {
            break outer;
          }

          sb.append(nextCharacter);
          low = symbolLow;
          high = symbolHigh;
          break inner;
        }
      }
    }

    return sb.toString();
  }
}
