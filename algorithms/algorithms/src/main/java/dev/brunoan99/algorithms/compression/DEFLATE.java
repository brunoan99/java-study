package dev.brunoan99.algorithms.compression;

import java.util.List;

public class DEFLATE {
  private Huffman huffman;

  public DEFLATE() {
  }

  public String compress(String input) {
    if (input == null || input.isEmpty()) {
      return "";
    }

    List<LZ77.Token> tokens = LZ77.compress(input);
    String compressedByLZ77 = LZ77.stringifyListOfTokens(tokens);

    Huffman huffman = new Huffman(compressedByLZ77);
    this.huffman = huffman;

    String encodedByHuffman = huffman.encode(compressedByLZ77);
    return encodedByHuffman;
  }

  public String decompress(String input) {
    if (input == null || input.isEmpty()) {
      return "";
    }

    String decodedByHuffman = huffman.decode(input);
    List<LZ77.Token> tokens = LZ77.listOfTokensFromString(decodedByHuffman);

    String decompressedByLZ77 = LZ77.decompress(tokens);
    return decompressedByLZ77;
  }
}
