package com.essaid.pcli.bootapp.one;

import com.essaid.pcli.spring.CommandsConfiguration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import(CommandsConfiguration.class)
public class MainApp {
  public static void main(String[] args) {
    BootApp bootApp = new BootApp(MainApp.class);
    
    bootApp.run(args);
    
  }
  
}
