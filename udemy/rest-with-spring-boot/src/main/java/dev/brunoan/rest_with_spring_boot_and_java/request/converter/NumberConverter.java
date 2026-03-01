package dev.brunoan.rest_with_spring_boot_and_java.request.converter;

import dev.brunoan.rest_with_spring_boot_and_java.exception.UnsupportedMathOperationException;

public class NumberConverter {
  public static double convertToDouble(String strNumber) throws Exception {
    try {
      if (strNumber == null || strNumber.isEmpty())
        throw new UnsupportedMathOperationException("Please set a numeric value!");

      String number = strNumber.replaceAll(",", ".");
      return Double.parseDouble(number);
    } catch (NumberFormatException e) {
      throw new UnsupportedMathOperationException("Please set a numeric value!");
    }
  }

}
