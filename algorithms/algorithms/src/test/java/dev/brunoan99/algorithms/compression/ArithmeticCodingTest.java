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

}
