package dev.brunoan.restwithspringbootandjava.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.brunoan.restwithspringbootandjava.math.SimpleMath;
import dev.brunoan.restwithspringbootandjava.request.converter.NumberConverter;

@RestController
@RequestMapping("/math")
public class MathController {

  @RequestMapping("/sum/{numberOne}/{numberTwo}")
  public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
      throws Exception {
    return SimpleMath.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
  }

  @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
  public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
      throws Exception {
    return SimpleMath.subtraction(NumberConverter.convertToDouble(numberOne),
        NumberConverter.convertToDouble(numberTwo));
  }

  @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
  public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
      throws Exception {
    return SimpleMath.multiplication(NumberConverter.convertToDouble(numberOne),
        NumberConverter.convertToDouble(numberTwo));
  }

  @RequestMapping("/division/{numberOne}/{numberTwo}")
  public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
      throws Exception {
    return SimpleMath.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
  }

  @RequestMapping("/mean/{numberOne}/{numberTwo}")
  public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
      throws Exception {
    return SimpleMath.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
  }

  @RequestMapping("/squareRoot/{number}")
  public Double squareRoot(@PathVariable("number") String number)
      throws Exception {
    return SimpleMath.squareRoot(NumberConverter.convertToDouble(number));
  }
}
