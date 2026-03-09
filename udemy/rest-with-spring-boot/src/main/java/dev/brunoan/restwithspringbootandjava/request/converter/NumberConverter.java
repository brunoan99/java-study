package dev.brunoan.restwithspringbootandjava.request.converter;

import dev.brunoan.restwithspringbootandjava.exception.UnsupportedMathOperationException;

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
