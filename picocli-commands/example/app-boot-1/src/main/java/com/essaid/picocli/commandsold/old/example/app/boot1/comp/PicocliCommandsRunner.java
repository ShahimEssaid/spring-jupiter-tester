package com.essaid.picocli.commandsold.old.example.app.boot1.comp;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class PicocliCommandsRunner implements CommandLineRunner, ApplicationContextAware {
  private ApplicationContext context;
  
  @Override
  public void run(String... args) throws Exception {
    
    System.out.println("=============== PicocliCommandsRunner args: " + Arrays.stream(args).toList() + " and context: " + context);
    
  }
  
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
  }
}
