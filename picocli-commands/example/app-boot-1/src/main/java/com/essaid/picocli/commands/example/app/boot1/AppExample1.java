package com.essaid.picocli.commands.example.app.boot1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import(AppExample1Configuration.class)
public class AppExample1 {
  
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(AppExample1.class, args);
    
  }
}
