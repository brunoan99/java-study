package dev.brunoan99.utilities;

import java.util.ArrayList;

public class Table {

  public static String formatTable(ArrayList<ArrayList<String>> table) {
    if (table == null || table.isEmpty())
      return "";

    int numCols = table.stream().mapToInt(ArrayList::size).max().orElse(0);
    int[] colWidths = new int[numCols];

    // Calc max width for each column
    for (ArrayList<String> row : table) {
      for (int c = 0; c < row.size(); c++) {
        colWidths[c] = Math.max(colWidths[c], row.get(c).trim().length());
      }
    }

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < table.size(); i++) {
      ArrayList<String> row = table.get(i);

      // Data row
      sb.append("| ");
      for (int c = 0; c < numCols; c++) {
        String cell = c < row.size() ? row.get(c).trim() : "";
        sb.append(String.format("%-" + colWidths[c] + "s", cell));
        sb.append(" | ");
      }
      sb.setLength(sb.length() - 1);
      sb.append("\n");

      // Separator after first row (header)
      if (i == 0) {
        sb.append("| ");
        for (int c = 0; c < numCols; c++) {
          sb.append("-".repeat(colWidths[c]));
          sb.append(" | ");
        }
        sb.setLength(sb.length() - 1);
        sb.append("\n");
      }
    }

    return sb.toString().trim();
  }
}
