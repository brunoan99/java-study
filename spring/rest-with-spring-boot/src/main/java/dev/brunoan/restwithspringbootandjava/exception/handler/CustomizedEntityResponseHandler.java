package dev.brunoan.restwithspringbootandjava.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.brunoan.restwithspringbootandjava.exception.ExceptionResponse;
import dev.brunoan.restwithspringbootandjava.exception.ResourceNotFoundException;
import dev.brunoan.restwithspringbootandjava.exception.UnsupportedMathOperationException;

@RestController
@ControllerAdvice
public class CustomizedEntityResponseHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    ExceptionResponse response = new ExceptionResponse(
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UnsupportedMathOperationException.class)
  public final ResponseEntity<Object> handleUnsupportedMathOperationException(UnsupportedMathOperationException ex,
      WebRequest request) {
    ExceptionResponse response = new ExceptionResponse(
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex,
      WebRequest request) {
    ExceptionResponse response = new ExceptionResponse(
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
