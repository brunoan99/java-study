package dev.brunoan99.algorithms.compression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HuffmanTest {

  @Test
  void testNullAndEmptyHandling() {
    Huffman huffman = new Huffman("");
    assertEquals("", huffman.encode(""));
    assertEquals("", huffman.decode(""));

    Huffman huffmanNull = new Huffman(null);
    assertEquals("", huffmanNull.encode(null));
    assertEquals("", huffmanNull.decode(null));
  }

  @Test
  void testEncode() {
    String input = "something to test this and that";
    Huffman huffman = new Huffman(input);

    String encoded = huffman.encode(input);
    assertNotNull(encoded);
    assertTrue(encoded.matches("[01]+"));
  }

  @Test
  void testDecode() {
    String input = "something to test this and that";
    Huffman huffman = new Huffman(input);

    String encoded = huffman.encode(input);
    String decoded = huffman.decode(encoded);
    assertEquals(input, decoded);
  }

  @Test
  void testFailWhenEncodeGetOtherTextThanInput() {
    String input = "xyz";
    String other = "abc";

    Huffman huffman = new Huffman(input);

    assertThrows(IllegalArgumentException.class,
        () -> huffman.encode(other));
  }

  @Test
  void testFailWhenDecodeSomethingDifferent() {
    String input = "xyz";
    Huffman huffman = new Huffman(input);
    String encoded = huffman.encode(input);
    String notEncoded = "2" + encoded + "3";

    assertThrows(IllegalArgumentException.class,
        () -> huffman.decode(notEncoded));
  }
}
