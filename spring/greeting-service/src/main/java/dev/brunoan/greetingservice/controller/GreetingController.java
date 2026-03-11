package dev.brunoan.greetingservice.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.brunoan.greetingservice.config.GreetingConfiguration;
import dev.brunoan.greetingservice.model.Greeting;

@RestController
public class GreetingController {

  private final AtomicLong counter = new AtomicLong();

  private GreetingConfiguration configuration;

  @Autowired
  public GreetingController(GreetingConfiguration configuration) {
    this.configuration = configuration;
  }

  @GetMapping("/greeting")
  public Greeting greeting(
      @RequestParam(value = "name", defaultValue = "") String name) {
    if (name.isBlank())
      name = configuration.defaultName();

    return new Greeting(counter.incrementAndGet(), String.format(configuration.template(), name));
  }
}
