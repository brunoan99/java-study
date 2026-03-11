package dev.brunoan.greetingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import dev.brunoan.greetingservice.config.GreetingConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(GreetingConfiguration.class)
public class Startup {

  public static void main(String[] args) {
    SpringApplication.run(Startup.class, args);
  }

}
