package dev.brunoan99.algorithms.compression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArithmeticCoding {
  private final FrequencyModel frequencyModel;
  private final int precision;
  private final Character END_OF_STRING = '\u2403';

  public ArithmeticCoding(String input) throws IllegalArgumentException {
    if (input == null || input.isEmpty())
      throw new IllegalArgumentException("Input cannot be empty");
    input = new StringBuilder(input).append(END_OF_STRING).toString();

    this.frequencyModel = new FrequencyModel(input);
    this.precision = calculatePrecision(input.length(), frequencyModel.getTotalFrequency());
  }

  public static class FrequencyModel {
    private final Map<Character, Integer> frequencies;
    private final Map<Character, Long> cumulativeFreq;
    private final List<Character> symbols;
    private final long totalFrequency;

    public FrequencyModel(String input) {
      // Count character frequencies
      frequencies = new HashMap<>();
      for (char c : input.toCharArray()) {
        frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
      }

      // Sort symbols for consistent ordering
      symbols = new ArrayList<>(frequencies.keySet());
      Collections.sort(symbols);

      // Build cumulative frequency table
      cumulativeFreq = new HashMap<>();
      long cumulative = 0;

      for (char symbol : symbols) {
        cumulativeFreq.put(symbol, cumulative);
        cumulative += frequencies.get(symbol);
      }

      this.totalFrequency = cumulative;
    }

    public FrequencyModel(Map<Character, Integer> freqs) {
      this.frequencies = new HashMap<>(freqs);

      // Sort symbols for consistent ordering
      symbols = new ArrayList<>(frequencies.keySet());
      Collections.sort(symbols);

      // Build cumulative frequency table
      cumulativeFreq = new HashMap<>();
      long cumulative = 0;

      for (char symbol : symbols) {
        cumulativeFreq.put(symbol, cumulative);
        cumulative += frequencies.get(symbol);
      }

      this.totalFrequency = cumulative;
    }

    public long getCumulativeFrequency(char symbol) {
      return cumulativeFreq.getOrDefault(symbol, 0L);
    }

    public long getFrequency(char symbol) {
      return frequencies.getOrDefault(symbol, 0);
    }

    public long getTotalFrequency() {
      return totalFrequency;
    }

    public char getSymbolByRange(BigInteger value) {
      // Find symbol whose cumulative range contains the value
      for (int i = 0; i < symbols.size(); i++) {
        char symbol = symbols.get(i);
        BigInteger cumFreq = BigInteger.valueOf(cumulativeFreq.get(symbol));
        BigInteger freq = BigInteger.valueOf(frequencies.get(symbol));
        BigInteger nextCumFreq = cumFreq.add(freq);

        // Check if value falls in [cumFreq, nextCumFreq)
        if (value.compareTo(cumFreq) >= 0 && value.compareTo(nextCumFreq) < 0) {
          return symbol;
        }
      }
      // Fallback
      return symbols.get(symbols.size() - 1);
    }

    public Map<Character, Integer> getFrequencies() {
      return new HashMap<>(frequencies);
    }
  }

  private int calculatePrecision(int length, long totalFreq) {
    // Estimate: each symbol needs log10(totalFreq) decimal digits
    // Add safety margin of 50%
    double log10Total = Math.log10(totalFreq);
    int required = (int) Math.ceil(length * log10Total * 1.5);

    // Minimum 100 digits, maximum 50000 digits (for very large inputs)
    return Math.max(100, Math.min(50000, required));
  }

  private BigInteger encode(String input) {
    FrequencyModel model = frequencyModel;
    int precision = calculatePrecision(input.length(), model.getTotalFrequency());

    BigInteger low = BigInteger.ZERO;
    BigInteger high = BigInteger.TEN.pow(precision);
    BigInteger total = BigInteger.valueOf(model.getTotalFrequency());

    for (char symbol : input.toCharArray()) {
      BigInteger range = high.subtract(low);
      BigInteger cumFreq = BigInteger.valueOf(model.getCumulativeFrequency(symbol));
      BigInteger freq = BigInteger.valueOf(model.getFrequency(symbol));

      // Narrow the range
      // new_high = low + floor(range * (cumFreq + freq) / total)
      // new_low = low + floor(range * cumFreq / total)
      BigInteger newHigh = low.add(range.multiply(cumFreq.add(freq)).divide(total));
      BigInteger newLow = low.add(range.multiply(cumFreq).divide(total));

      low = newLow;
      high = newHigh;
    }

    // Return midpoint of final range
    return low.add(high.subtract(low).divide(BigInteger.TWO));
  }

  public BigDecimal compress(String input) throws IllegalArgumentException {
    if (input == null || input.isEmpty())
      throw new IllegalArgumentException("Input cannot be empty");
    input = new StringBuilder(input).append(END_OF_STRING).toString();

    // Encode
    BigInteger value = encode(input);

    // Convert to BigDecimal
    BigInteger scale = BigInteger.TEN.pow(precision);
    BigDecimal compressed = new BigDecimal(value).divide(
        new BigDecimal(scale), precision, RoundingMode.HALF_UP);

    return compressed;
  }

  private String decode(BigInteger value, int precision) {
    StringBuilder result = new StringBuilder();
    FrequencyModel model = frequencyModel;

    BigInteger low = BigInteger.ZERO;
    BigInteger high = BigInteger.TEN.pow(precision);
    BigInteger total = BigInteger.valueOf(model.getTotalFrequency());

    while (true) {
      BigInteger range = high.subtract(low);

      // Find which symbol this value corresponds to
      // scaledValue = floor((value - low) * total / range)
      BigInteger scaledValue = value.subtract(low).multiply(total).divide(range);

      // Get the symbol
      char symbol = model.getSymbolByRange(scaledValue);
      if (symbol == END_OF_STRING)
        break;
      result.append(symbol);

      // Update range to match encoding
      BigInteger cumFreq = BigInteger.valueOf(model.getCumulativeFrequency(symbol));
      BigInteger freq = BigInteger.valueOf(model.getFrequency(symbol));

      BigInteger newHigh = low.add(range.multiply(cumFreq.add(freq)).divide(total));
      BigInteger newLow = low.add(range.multiply(cumFreq).divide(total));

      low = newLow;
      high = newHigh;
    }
    // for (int i = 0; i < inputLength; i++) {
    // BigInteger range = high.subtract(low);

    // // Find which symbol this value corresponds to
    // // scaledValue = floor((value - low) * total / range)
    // BigInteger scaledValue = value.subtract(low).multiply(total).divide(range);

    // // Get the symbol
    // char symbol = model.getSymbolByRange(scaledValue);
    // result.append(symbol);

    // // Update range to match encoding
    // BigInteger cumFreq =
    // BigInteger.valueOf(model.getCumulativeFrequency(symbol));
    // BigInteger freq = BigInteger.valueOf(model.getFrequency(symbol));

    // BigInteger newHigh =
    // low.add(range.multiply(cumFreq.add(freq)).divide(total));
    // BigInteger newLow = low.add(range.multiply(cumFreq).divide(total));

    // low = newLow;
    // high = newHigh;
    // }

    return result.toString();
  }

  public String decompress(BigDecimal input) throws IllegalArgumentException {
    if (input == BigDecimal.ZERO || input == null)
      throw new IllegalArgumentException("Input cannot be zero");

    // Convert BigDecimal back to BigInteger value
    BigInteger scale = BigInteger.TEN.pow(precision);
    BigInteger value = input.multiply(new BigDecimal(scale))
        .toBigInteger();

    // Decode
    return decode(value, precision);
  }
}
