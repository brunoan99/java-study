package dev.brunoan99.algorithms.compression;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArithmeticCodingTest {

  @Test
  void testFailWhenConstructorGetInvalidInput() {
    assertThrows(IllegalArgumentException.class, () -> new ArithmeticCoding(""));
    assertThrows(IllegalArgumentException.class, () -> new ArithmeticCoding(null));
  }

  @Test
  void testFailWhenCompressGetInvalidInput() {
    ArithmeticCoding ac = new ArithmeticCoding("test");
    assertThrows(IllegalArgumentException.class, () -> ac.compress(""));
    assertThrows(IllegalArgumentException.class, () -> ac.compress(null));
  }

  @Test
  void testFailWhenDecompressGetInvalidInput() {
    ArithmeticCoding ac = new ArithmeticCoding("test");
    assertThrows(IllegalArgumentException.class, () -> ac.decompress(null));
  }

  @Test
  void testCompressAndDecompress() {
    String input = "ababcbababaa";
    ArithmeticCoding ac = new ArithmeticCoding(input);
    BigDecimal compressed = ac.compress(input);

    assertNotNull(compressed);
    assertTrue(compressed.compareTo(BigDecimal.ZERO) >= 0);
    assertTrue(compressed.compareTo(BigDecimal.ONE) < 0);

    String decompressed = ac.decompress(compressed);
    assertEquals(input, decompressed);
  }

  @Test
  void testCompressSequenceOfSameSymbolsShouldReturnCloseToZeroButNotZero() {
    String fakeInput = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    ArithmeticCoding ac = new ArithmeticCoding(fakeInput);
    String i1 = "AAAAA"; // 5 A's
    BigDecimal c1 = ac.compress(i1);

    assertNotNull(c1);
    assertTrue(c1.compareTo(BigDecimal.ZERO) > 0);

    String d1 = ac.decompress(c1);
    assertEquals(i1, d1);

    String i2 = "AAAAAAAAAA"; // 10 A's
    BigDecimal c2 = ac.compress(i2);
    assertNotNull(c2);
    assertTrue(c2.compareTo(BigDecimal.ZERO) > 0);

    String d2 = ac.decompress(c2);
    assertEquals(i2, d2);

    // C1 should be greater than C2 cause
    // the probability distribution of the symbols used is the same for both inputs,
    // and its a number 0 < x < 1;
    // and cause it is only this symbols and the used to end of symbol
    // in this particular case that the possible range will always be [0, x)
    // so the longer the input, the more of the possible range is shrinked
    assertTrue(c1.compareTo(c2) == 1);
  }
}
