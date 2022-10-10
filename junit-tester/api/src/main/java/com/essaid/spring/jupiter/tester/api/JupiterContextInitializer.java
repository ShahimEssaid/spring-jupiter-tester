package com.essaid.spring.jupiter.tester.api;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class JupiterContextInitializer implements ApplicationContextInitializer {
  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    System.out.println("============= INITIALIZING  ===========");

  }
}
