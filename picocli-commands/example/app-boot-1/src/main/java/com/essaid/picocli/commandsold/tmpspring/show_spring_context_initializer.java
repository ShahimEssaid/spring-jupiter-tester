package com.essaid.picocli.commandsold.tmpspring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class show_spring_context_initializer {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    context.refresh();
  }
}
