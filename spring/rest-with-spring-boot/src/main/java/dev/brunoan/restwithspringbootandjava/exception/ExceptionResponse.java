package dev.brunoan.restwithspringbootandjava.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}
