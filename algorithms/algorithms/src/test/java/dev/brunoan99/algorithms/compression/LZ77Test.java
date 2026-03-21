package dev.brunoan99.algorithms.compression;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LZ77Test {

  @Test
  void testNullAndEmptyHandling() {
    assertEquals(new ArrayList<>(), LZ77.compress(""));
    assertEquals("", LZ77.decompress(new ArrayList<>()));

    assertEquals(new ArrayList<>(), LZ77.compress(null));
    assertEquals("", LZ77.decompress(null));
  }

  @Test
  void testCompressAndDecompress() {
    String original = "ababcbababaa";
    List<LZ77.Token> compressed = LZ77.compress(original, 10, 4);
    String decompressed = LZ77.decompress(compressed);
    assertEquals(original, decompressed);
  }

  @Test
  void testCompressAndDecompressUsingStrings() {
    String original = "ababcbababaa";
    List<LZ77.Token> tokens = LZ77.compress(original, 10, 4);
    String compressed = LZ77.stringifyListOfTokens(tokens);
    List<LZ77.Token> tokensFromString = LZ77.listOfTokensFromString(compressed);
    String decompressed = LZ77.decompress(tokensFromString);
    assertEquals(original, decompressed);
  }

  @Test
  void testFailWhenGetInvalidWindowSize() {
    assertThrows(IllegalArgumentException.class, () -> LZ77.compress("test", 0, 5));
  }

  @Test
  void testFailWhenGetInvalidLookaheadBufferSize() {
    assertThrows(IllegalArgumentException.class, () -> LZ77.compress("test", 1, -1));
  }
}
