package dev.brunoan99.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

  public static void write(String path, String content) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
      writer.write(content);
    }
  }

}
