package dev.brunoan.restwithspringbootandjava.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLogController {

  private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

  @GetMapping("/test-log")
  public String testLog() {
    logger.debug("Test log message with debug level");
    logger.info("Test log message with info level");
    logger.warn("Test log message with warn level");
    logger.error("Test log message with error level");
    return "Logs generated succesfully!";
  }
}
