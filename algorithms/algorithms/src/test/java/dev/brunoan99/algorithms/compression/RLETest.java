package dev.brunoan99.algorithms.compression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RLETest {
  @Test
  public void testNullInputs() {
    assertEquals("", RLE.compress(null));
    assertEquals("", RLE.decompress(null));
  }

  @Test
  public void testCompress() {
    String input = "aaabbcaaa";
    String expectedOutput = "3a2b1c3a";
    String actualOutput = RLE.compress(input);
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testDecompress() {
    String input = "3a2b1c3a";
    String expectedOutput = "aaabbcaaa";
    String actualOutput = RLE.decompress(input);
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testCompressAndDecompress() {
    String input = "wwwwaaadexxxxxx";
    String compressed = RLE.compress(input);
    String decompressed = RLE.decompress(compressed);
    assertEquals(input, decompressed);
  }
}
