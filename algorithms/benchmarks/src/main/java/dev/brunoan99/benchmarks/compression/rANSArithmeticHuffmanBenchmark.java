package dev.brunoan99.benchmarks.compression;

import dev.brunoan99.algorithms.compression.ArithmeticCoding;
import dev.brunoan99.algorithms.compression.Huffman;
import dev.brunoan99.algorithms.compression.rANS;
import dev.brunoan99.utilities.Accumulator;
import dev.brunoan99.utilities.BenchmarkRunner;
import dev.brunoan99.utilities.RandomInputHelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class rANSArithmeticHuffmanBenchmark
    extends BenchmarkRunner<rANSArithmeticHuffmanBenchmark.ResultLine, rANSArithmeticHuffmanBenchmark.ResultFinal> {
  public rANSArithmeticHuffmanBenchmark() {
    super(new GeneralConfig());
  }

  public rANSArithmeticHuffmanBenchmark(GeneralConfig config) {
    super(config);
  }

  record ResultLine(
      int originalSize,
      int arithmeticCodingCompressedSize,
      long arithmeticCodingInitializingTime,
      long arithmeticCodingCompressingTime,
      long arithmeticCodingDecompressingTime,

      int huffmanCompressedSize,
      long huffmanInitializingTime,
      long huffmanCompressingTime,
      long huffmanDecompressingTime,

      int rANSCompressedSize,
      long rANSInitializingTime,
      long rANSCompressingTime,
      long rANSDecompressingTime) {
  }

  record ResultFinal(
      int count,
      float meanOriginalSize,

      float meanArithmeticCodingCompressedSize,
      float meanArithmeticCodingCompressingRatio,
      float meanArithmeticCodingInitializingTime,
      float meanArithmeticCodingCompressingTime,
      float meanArithmeticCodingDecompressingTime,

      float meanHuffmanCompressedSize,
      float meanHuffmanCompressingRatio,
      float meanHuffmanInitializingTime,
      float meanHuffmanCompressingTime,
      float meanHuffmanDecompressingTime,

      float meanrANSCompressedSize,
      float meanrANSCompressingRatio,
      float meanrANSInitializingTime,
      float meanrANSCompressingTime,
      float meanrANSDecompressingTime) {
  }

  public static class rANSArithmeticHuffmanBenchmarkAccumulator
      implements Accumulator<rANSArithmeticHuffmanBenchmark.ResultLine, rANSArithmeticHuffmanBenchmark.ResultFinal> {
    int count = 0;
    float meanOriginalSize = 0L;

    float meanrANSCompressedSize = 0L;
    float meanrANSCompressingRatio = 0L;
    float meanrANSInitializingTime = 0L;
    float meanrANSCompressingTime = 0L;
    float meanrANSDecompressingTime = 0L;

    float meanArithmeticCodingCompressedSize = 0L;
    float meanArithmeticCodingCompressingRatio = 0L;
    float meanArithmeticCodingInitializingTime = 0L;
    float meanArithmeticCodingCompressingTime = 0L;
    float meanArithmeticCodingDecompressingTime = 0L;

    float meanHuffmanCompressedSize = 0L;
    float meanHuffmanCompressingRatio = 0L;
    float meanHuffmanInitializingTime = 0L;
    float meanHuffmanCompressingTime = 0L;
    float meanHuffmanDecompressingTime = 0L;

    @Override
    public void add(ResultLine v) {
      count++;
      meanOriginalSize += v.originalSize;

      meanrANSCompressedSize += v.rANSCompressedSize;
      meanrANSCompressingRatio += ((float) v.rANSCompressedSize / (float) v.originalSize);
      meanrANSInitializingTime += v.rANSInitializingTime;
      meanrANSCompressingTime += v.rANSCompressingTime;
      meanrANSDecompressingTime += v.rANSDecompressingTime;

      meanArithmeticCodingCompressedSize += v.arithmeticCodingCompressedSize;
      meanArithmeticCodingCompressingRatio += ((float) v.arithmeticCodingCompressedSize / (float) v.originalSize);
      meanArithmeticCodingInitializingTime += v.arithmeticCodingInitializingTime;
      meanArithmeticCodingCompressingTime += v.arithmeticCodingCompressingTime;
      meanArithmeticCodingDecompressingTime += v.arithmeticCodingDecompressingTime;

      meanHuffmanCompressedSize += v.huffmanCompressedSize;
      meanHuffmanCompressingRatio += ((float) v.huffmanCompressedSize / (float) v.originalSize);
      meanHuffmanInitializingTime += v.huffmanInitializingTime;
      meanHuffmanCompressingTime += v.huffmanCompressingTime;
      meanHuffmanDecompressingTime += v.huffmanDecompressingTime;
    }

    @Override
    public ResultFinal result() {
      return new ResultFinal(
          count,
          meanOriginalSize / count,

          meanrANSCompressedSize / count,
          meanrANSCompressingRatio / count,
          meanrANSInitializingTime / count,
          meanrANSCompressingTime / count,
          meanrANSDecompressingTime / count,

          meanArithmeticCodingCompressedSize / count,
          meanArithmeticCodingCompressingRatio / count,
          meanArithmeticCodingInitializingTime / count,
          meanArithmeticCodingCompressingTime / count,
          meanArithmeticCodingDecompressingTime / count,

          meanHuffmanCompressedSize / count,
          meanHuffmanCompressingRatio / count,
          meanHuffmanInitializingTime / count,
          meanHuffmanCompressingTime / count,
          meanHuffmanDecompressingTime / count);
    }
  }

  @Override
  protected ResultLine processFunction(RandomInputHelper.InputLine inputLine) {
    String text = inputLine.value();

    long ArithmeticCodingInitializingStartTime = System.nanoTime();
    ArithmeticCoding ac = new ArithmeticCoding(text);
    long ArithmeticCodingInitializingEndTime = System.nanoTime();
    long ArithmeticCodingInitializingTime = ArithmeticCodingInitializingEndTime
        - ArithmeticCodingInitializingStartTime;

    long ArithmeticCodingCompressingStartTime = System.nanoTime();
    BigDecimal compressedArithmeticCoding = ac.compress(text);
    long ArithmeticCodingCompressingEndTime = System.nanoTime();
    long ArithmeticCodingCompressingTime = ArithmeticCodingCompressingEndTime - ArithmeticCodingCompressingStartTime;

    long ArithmeticCodingDecompressingStartTime = System.nanoTime();
    String decompressedArithmeticCoding = ac.decompress(compressedArithmeticCoding);
    long ArithmeticCodingDecompressingEndTime = System.nanoTime();
    long ArithmeticCodingDecompressingTime = ArithmeticCodingDecompressingEndTime
        - ArithmeticCodingDecompressingStartTime;

    if (!text.equals(decompressedArithmeticCoding)) {
      throw new RuntimeException(
          "Decompressed string does not match original for Arithmetic Coding algorithm on text: \n\n" + text
              + "\n\n");
    }

    long HuffmanInitializingStartTime = System.nanoTime();
    Huffman huffman = new Huffman(text);
    long HuffmanInitializingEndTime = System.nanoTime();
    long HuffmanInitializingTime = HuffmanInitializingEndTime - HuffmanInitializingStartTime;

    long HuffmanCompressingStartTime = System.nanoTime();
    String compressedHuffman = huffman.encode(text);
    long HuffmanCompressingEndTime = System.nanoTime();
    long HuffmanCompressingTime = HuffmanCompressingEndTime - HuffmanCompressingStartTime;

    long HuffmanDecompressingStartTime = System.nanoTime();
    String decompressedHuffman = huffman.decode(compressedHuffman);
    long HuffmanDecompressingEndTime = System.nanoTime();
    long HuffmanDecompressingTime = HuffmanDecompressingEndTime - HuffmanDecompressingStartTime;

    if (!text.equals(decompressedHuffman)) {
      throw new RuntimeException(
          "Decompressed string does not match original for Huffman algorithm on text: \n\n" + text + "\n\n");
    }

    long rANSInitializingStartTime = System.nanoTime();
    rANS rans = new rANS(text);
    long rANSInitializingEndTime = System.nanoTime();
    long rANSInitializingTime = rANSInitializingEndTime - rANSInitializingStartTime;

    long rANSCompressingStartTime = System.nanoTime();
    BigInteger compressedrANS = rans.compress(text);
    long rANSCompressingEndTime = System.nanoTime();
    long rANSCompressingTime = rANSCompressingEndTime - rANSCompressingStartTime;

    long rANSDecompressingStartTime = System.nanoTime();
    String decompressedrANS = rans.decompress(compressedrANS);
    long rANSDecompressingEndTime = System.nanoTime();
    long rANSDecompressingTime = rANSDecompressingEndTime - rANSDecompressingStartTime;

    if (!text.equals(decompressedrANS)) {
      throw new RuntimeException(
          "Decompressed string does not match original for rANS algorithm on text: \n\n" + text + "\n\n");
    }

    int originalSize = text.length(); // each character represents a byte in UTF-8 encoding
    int ArithmeticCodingCompressedSize = (compressedArithmeticCoding.unscaledValue().bitCount() / 8) + 4;
    int HuffmanCompressedSize = compressedHuffman.length() / 8;
    int rANSCompressedSize = compressedrANS.bitLength() / 8;

    return new ResultLine(
        originalSize,

        ArithmeticCodingCompressedSize,
        ArithmeticCodingInitializingTime,
        ArithmeticCodingCompressingTime,
        ArithmeticCodingDecompressingTime,

        HuffmanCompressedSize,
        HuffmanInitializingTime,
        HuffmanCompressingTime,
        HuffmanDecompressingTime,

        rANSCompressedSize,
        rANSInitializingTime,
        rANSCompressingTime,
        rANSDecompressingTime);
  }

  @Override
  protected ArrayList<ArrayList<String>> formatFunction(Map<InputParam, ResultFinal> resMap) {
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    table.add(new ArrayList<String>(
        Arrays.asList(
            "Length",
            "Sequence",
            "Tests Number",
            "rANS Compression Ratio",
            "rANS TotalTime",
            "A.C. Compression Ratio",
            "A.C. TotalTime",
            "Huf. Compression Ratio",
            "Huf. TotalTime")));
    resMap.entrySet().stream()
        .sorted(java.util.Comparator
            .comparingInt(
                (java.util.Map.Entry<InputParam, ResultFinal> e) -> e.getKey().randomStringLength())
            .thenComparingInt(e -> e.getKey().maxSequenceLength()))
        .forEach(entry -> {
          InputParam input = entry.getKey();
          ResultFinal resfinal = entry.getValue();
          table.add(new ArrayList<String>(
              Arrays.asList(
                  String.valueOf(input.randomStringLength()),
                  String.valueOf(input.maxSequenceLength()),
                  String.valueOf(resfinal.count),

                  String.format("%.10f", resfinal.meanrANSCompressingRatio),
                  String.format("%,.0f",
                      resfinal.meanrANSInitializingTime + resfinal.meanrANSCompressingTime
                          + resfinal.meanrANSDecompressingTime),

                  String.format("%.10f", resfinal.meanArithmeticCodingCompressingRatio),
                  String.format("%,.0f",
                      resfinal.meanArithmeticCodingInitializingTime + resfinal.meanArithmeticCodingCompressingTime
                          + resfinal.meanArithmeticCodingDecompressingTime),

                  String.format("%.10f", resfinal.meanHuffmanCompressingRatio),
                  String.format("%,.0f", resfinal.meanHuffmanInitializingTime + resfinal.meanHuffmanCompressingTime
                      + resfinal.meanHuffmanDecompressingTime))));
        });
    return table;
  }

  @Override
  protected Accumulator<ResultLine, ResultFinal> accumulatorFactory() {
    return new rANSArithmeticHuffmanBenchmarkAccumulator();
  }
}
