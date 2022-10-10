package com.essaid.spring.jupiter.tester.jupiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JupiterBoot {
  
  private static JupiterCommand command;
  private static SpringApplication application;
  private static ConfigurableApplicationContext context;
  

  public static void start(JupiterCommand command){
    JupiterBoot.command = command;
    System.out.println("========= Jupiter Application called  ============== ");
    application = new SpringApplication(JupiterBoot.class);
    context = application.run();
    context.start();
  }

}
