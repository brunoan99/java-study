package dev.brunoan99.algorithms.compression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DEFLATETest {

  @Test
  void testNullAndEmptyHandling() {
    DEFLATE deflate = new DEFLATE();
    assertEquals("", deflate.compress(""));
    assertEquals("", deflate.decompress(""));

    deflate = new DEFLATE();
    assertEquals("", deflate.compress(null));
    assertEquals("", deflate.decompress(null));
  }

  @Test
  void testCompressAndDecompress() {
    String input = "ababcbababaa";
    DEFLATE deflate = new DEFLATE();
    String compressed = deflate.compress(input);
    String decompressed = deflate.decompress(compressed);
    assertEquals(input, decompressed);
  }

  @Test
  void testFailWhenDecodeSomethingDifferent() {
    String input = "xyz";
    DEFLATE deflate = new DEFLATE();
    String encoded = deflate.compress(input);
    String notEncoded = "2" + encoded + "3";

    assertThrows(IllegalArgumentException.class,
        () -> deflate.decompress(notEncoded));
  }

}
