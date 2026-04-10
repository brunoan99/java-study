package dev.brunoan99.algorithms.compression;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class rANSTest {

  @Test
  void testFailWhenConstructorGetInvalidInput() {
    assertThrows(IllegalArgumentException.class, () -> new rANS(""));
    assertThrows(IllegalArgumentException.class, () -> new rANS(null));
  }

  @Test
  void testFailWhenCompressGetInvalidInput() {
    rANS ac = new rANS("test");
    assertThrows(IllegalArgumentException.class, () -> ac.compress(""));
    assertThrows(IllegalArgumentException.class, () -> ac.compress(null));
  }

  @Test
  void testFailWhenDecompressGetInvalidInput() {
    rANS ac = new rANS("test");
    assertThrows(IllegalArgumentException.class, () -> ac.decompress(null));
  }

  @Test
  void testCompressAndDecompress() {
    String input = "ababcbababaa";
    rANS ac = new rANS(input);
    BigInteger compressed = ac.compress(input);

    assertNotNull(compressed);
    assertTrue(compressed.compareTo(BigInteger.ZERO) >= 0);

    String decompressed = ac.decompress(compressed);
    assertEquals(input, decompressed);
  }

  @Test
  void testCalculateQuantizedFrequencies() {
    String input = "ggggggggggggggggggggggggggggggggggggggggggggggggggggggggcccceeUN";
    assertDoesNotThrow(() -> new rANS(input));
  }
}
