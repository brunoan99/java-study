package dev.brunoan99.algorithms.compression;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

  private Node root;
  private final Map<Character, String> huffmanCodes;

  private static class Node implements Comparable<Node> {
    final char ch;
    final int freq;
    final Node left;
    final Node right;

    Node(char ch, int freq) {
      this.ch = ch;
      this.freq = freq;
      this.left = null;
      this.right = null;
    }

    Node(int freq, Node left, Node right) {
      this.ch = '\0';
      this.freq = freq;
      this.left = left;
      this.right = right;
    }

    boolean isLeaft() {
      return left == null && right == null;
    }

    @Override
    public int compareTo(Node arg0) {
      return Integer.compare(this.freq, arg0.freq);
    }
  }

  private static class TreeBuilder {
    private TreeBuilder() {
    }

    private static Map<Character, Integer> generateFreqMap(String input) {
      Map<Character, Integer> freqMap = new HashMap<>();
      for (char c : input.toCharArray()) {
        freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
      }
      return freqMap;
    }

    private static PriorityQueue<Node> generatePriorityQueue(Map<Character, Integer> freqMap) {
      PriorityQueue<Node> pq = new PriorityQueue<>();
      for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
        pq.add(new Node(entry.getKey(), entry.getValue()));
      }
      return pq;
    }

    private static Node generateTreeByPriorityQueue(PriorityQueue<Node> prioQueue) {
      while (prioQueue.size() > 1) {
        Node left = prioQueue.poll();
        Node right = prioQueue.poll();
        prioQueue.add(new Node(left.freq + right.freq, left, right));
      }
      return prioQueue.poll();
    }

    public static Node generateTree(String input) {
      Map<Character, Integer> freqMap = generateFreqMap(input);

      PriorityQueue<Node> prioQueue = generatePriorityQueue(freqMap);

      return generateTreeByPriorityQueue(prioQueue);
    }
  }

  private static class CodeMapBuilder {
    private CodeMapBuilder() {
    }

    private static void generateCodes(Node node, String code, Map<Character, String> map) {
      if (node == null)
        return;

      if (node.isLeaft()) {
        map.put(node.ch, code);
        return;
      }

      generateCodes(node.left, code + "0", map);
      generateCodes(node.right, code + "1", map);
    }

    public static void populateCodeMap(Map<Character, String> tempMap, Node root) {
      generateCodes(root, "", tempMap);

      if (tempMap.size() == 1) {
        tempMap.put(root.ch, "0");
      }
    }

  }

  public Huffman(String input) {
    if (input == null || input.isEmpty()) {
      this.huffmanCodes = Collections.emptyMap();
      return;
    }

    root = TreeBuilder.generateTree(input);

    Map<Character, String> tempMap = new HashMap<>();
    CodeMapBuilder.populateCodeMap(tempMap, root);

    huffmanCodes = Collections.unmodifiableMap(tempMap);
  }

  public String encode(String input) {
    if (input == null || input.isEmpty())
      return "";

    if (root == null)
      throw new IllegalStateException("Tree is Empty");

    StringBuilder sb = new StringBuilder();

    for (char c : input.toCharArray()) {
      if (!huffmanCodes.containsKey(c)) {
        throw new IllegalArgumentException(
            String.format("Character '%c' (U+%04X) not found in CodeMap.", c, (int) c));
      }
      sb.append(huffmanCodes.get(c));
    }
    return sb.toString();
  }

  public String decode(String input) {
    if (input == null || input.isEmpty())
      return "";

    if (root == null)
      throw new IllegalStateException("Tree is Empty");

    StringBuilder sb = new StringBuilder();

    if (root.isLeaft()) {
      for (char ch : input.toCharArray()) {
        if (ch != '0')
          throw new IllegalArgumentException("Invalid binary Sequence");
        sb.append(root.ch);
      }
      return sb.toString();
    }

    Node current = root;
    for (char ch : input.toCharArray()) {
      if (ch != '0' && ch != '1')
        throw new IllegalArgumentException("Invalid characters: " + ch);

      current = (ch == '0') ? current.left : current.right;

      if (current.isLeaft()) {
        sb.append(current.ch);
        current = root;
      }

    }

    if (current != root) {
      throw new IllegalArgumentException("Incomplete sequence");
    }

    return sb.toString();
  }

}
