package dev.brunoan99.algorithms.compression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RLECompressorTest {
  @Test
  public void testNullInputs() {
    assertEquals("", RLECompressor.compress(null));
    assertEquals("", RLECompressor.decompress(null));
  }

  @Test
  public void testCompress() {
    String input = "aaabbcaaa";
    String expectedOutput = "3a2b1c3a";
    String actualOutput = RLECompressor.compress(input);
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testDecompress() {
    String input = "3a2b1c3a";
    String expectedOutput = "aaabbcaaa";
    String actualOutput = RLECompressor.decompress(input);
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testCompressAndDecompress() {
    String input = "wwwwaaadexxxxxx";
    String compressed = RLECompressor.compress(input);
    String decompressed = RLECompressor.decompress(compressed);
    assertEquals(input, decompressed);
  }
}
