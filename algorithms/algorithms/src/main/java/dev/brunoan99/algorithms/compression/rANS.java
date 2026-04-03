package dev.brunoan99.algorithms.compression;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class rANS {
  private final int inputLength;
  private final long largestRemainder;
  private final Map<Character, Intervals> quantizedFrequencies;

  public rANS(String input) {
    if (input == null || input.isEmpty())
      throw new IllegalArgumentException("Input cannot be null or empty");
    this.inputLength = input.length();

    Long uniqueCaracters = input.chars().distinct().count();
    this.largestRemainder = Math.powExact(2, uniqueCaracters.intValue() - 1);

    Map<Character, Intervals> map = calculateQuantizedFrequencies(input);
    this.quantizedFrequencies = Collections.unmodifiableMap(map);
  }

  public rANS(String input, int largestRemainder) {
    if (input == null || input.isEmpty())
      throw new IllegalArgumentException("Input cannot be null or empty");
    this.inputLength = input.length();

    this.largestRemainder = largestRemainder;

    this.quantizedFrequencies = calculateQuantizedFrequencies(input);

  }

  public record Intervals(int quantizedFrequency, int start, int end) {
  }

  public Map<Character, Intervals> calculateQuantizedFrequencies(String input) {
    /*
     * quantizedFrequencies calculated by Largest Remainder Method (LRM)
     */
    Map<Character, Integer> frequencies = new HashMap<>();
    for (char character : input.toCharArray()) {
      frequencies.put(character, frequencies.getOrDefault(character, 0) + 1);
    }

    if (frequencies.isEmpty())
      return Collections.emptyMap();

    Map<Character, Double> probabilities = new HashMap<>();
    Map<Character, Integer> flooredValues = new HashMap<>();
    Map<Character, Double> RemainingValues = new HashMap<>();

    for (Map.Entry<Character, Integer> e : frequencies.entrySet()) {
      double probability = (double) e.getValue() / inputLength;
      probabilities.put(e.getKey(), probability);
      int floor = (int) Math.floor(probability * largestRemainder);
      flooredValues.put(e.getKey(), floor);
      RemainingValues.put(e.getKey(), probability - floor);
    }

    int sumQuantizedValues = flooredValues.values().stream().mapToInt(Integer::intValue).sum();
    int remaining = (int) largestRemainder - sumQuantizedValues;

    List<Character> symbols = frequencies.keySet().stream().sorted((a, b) -> {
      int cmp = Double.compare(probabilities.get(a), probabilities.get(b));
      if (cmp != 0)
        return cmp;
      cmp = Integer.compare(frequencies.get(b), frequencies.get(a));
      if (cmp != 0)
        return cmp;
      return Character.compare(a, b);
    }).collect(Collectors.toList());

    Map<Character, Integer> frequenciesQuantized = new HashMap<>();
    for (Character symbol : symbols) {
      frequenciesQuantized.put(symbol, flooredValues.get(symbol));
    }
    for (int i = 0; i < remaining; i++) {
      Character symbol = symbols.get(i % symbols.size());
      frequenciesQuantized.put(symbol, frequenciesQuantized.get(symbol) + 1);
    }

    List<Character> zeros = frequenciesQuantized.entrySet().stream().filter(en -> en.getValue() == 0)
        .map(Map.Entry::getKey).collect(Collectors.toList());
    if (!zeros.isEmpty()) {
      for (Character symbol : zeros) {
        frequenciesQuantized.put(symbol, 1);
      }
    }

    int diff = frequenciesQuantized.values().stream().mapToInt(Integer::intValue).sum() - (int) largestRemainder;
    if (diff > 0) {
      List<Character> decreaseCandidates = symbols.stream().filter(symbol -> frequenciesQuantized.get(symbol) > 1)
          .sorted((a, b) -> {
            int cmp = Double.compare(RemainingValues.get(a), RemainingValues.get(b));
            if (cmp != 0)
              return cmp;
            return Character.compare(a, b);
          }).collect(Collectors.toList());
      int index = 0;
      while (diff > 0 && index < decreaseCandidates.size()) {
        Character candidate = decreaseCandidates.get(index++);
        frequenciesQuantized.put(candidate, frequenciesQuantized.get(candidate) - 1);
        diff--;
      }
      if (diff > 0)
        throw new IllegalStateException("Unable to adjust frequencies to match largestRemainder");
    }

    Map<Character, Intervals> quantizedWithIntervals = new HashMap<>();
    int cursor = 0;
    List<Character> sortedSymbols = new ArrayList<>(frequenciesQuantized.keySet());
    sortedSymbols.sort(Comparator.naturalOrder());
    for (Character symbol : sortedSymbols) {
      int quantizedFrequency = frequenciesQuantized.get(symbol);
      quantizedWithIntervals.put(symbol, new Intervals(quantizedFrequency, cursor, cursor + quantizedFrequency - 1));
      cursor += quantizedFrequency;
    }

    if (cursor != largestRemainder)
      throw new IllegalStateException("Sum of quantized frequencies does not match largestRemainder");

    return quantizedWithIntervals;
  }

  public BigInteger compress(String input) {
    if (input == null || input.isEmpty())
      throw new IllegalArgumentException("Input cannot be null or empty");

    BigInteger state = BigInteger.valueOf(largestRemainder);
    BigInteger largestRemainder = BigInteger.valueOf(this.largestRemainder);

    char[] reversedCharSequence = new char[input.length()];
    for (int i = 0; i < input.length(); i++) {
      reversedCharSequence[i] = input.charAt(input.length() - i - 1);
    }
    for (char symbol : reversedCharSequence) {
      Intervals intervals = quantizedFrequencies.get(symbol);
      if (intervals == null)
        throw new IllegalStateException("Symbol not found in quantized frequencies: " + symbol);

      BigInteger frequency = BigInteger.valueOf(intervals.quantizedFrequency());
      BigInteger start = BigInteger.valueOf(intervals.start());

      BigInteger q = state.divide(frequency);
      BigInteger m = state.remainder(frequency);
      BigInteger newState = q.multiply(largestRemainder).add(start).add(m);

      state = newState;
    }

    return state;
  }

  public String decompress(BigInteger input) {
    if (input == null)
      throw new IllegalArgumentException("Input cannot be null or empty");

    BigInteger state = input;
    BigInteger largestRemainder = BigInteger.valueOf(this.largestRemainder);

    char[] residueToSymbol = new char[largestRemainder.intValue()];
    Arrays.fill(residueToSymbol, '\0');
    for (Map.Entry<Character, Intervals> entry : quantizedFrequencies.entrySet()) {
      char symbol = entry.getKey();
      Intervals intervals = entry.getValue();
      for (int i = intervals.start(); i <= intervals.end(); i++) {
        residueToSymbol[i] = symbol;
      }
    }

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < inputLength; i++) {
      int r = state.remainder(largestRemainder).intValue();
      if (r < 0 || r >= residueToSymbol.length)
        throw new IllegalStateException("Residue out of range: " + r);
      char symbol = residueToSymbol[r];
      if (symbol == '\0')
        throw new IllegalStateException("No symbol found for residue: " + r);

      sb.append(symbol);

      Intervals intervals = quantizedFrequencies.get(symbol);
      if (intervals == null)
        throw new IllegalStateException("Symbol not found in quantized frequencies: " + symbol);

      BigInteger frequency = BigInteger.valueOf(intervals.quantizedFrequency());
      BigInteger start = BigInteger.valueOf(intervals.start());

      BigInteger previusState = frequency.multiply(state.divide(largestRemainder))
          .add(BigInteger.valueOf(r).subtract(start));

      state = previusState;
    }

    return sb.toString();
  }

}
