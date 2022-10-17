package com.essaid.picocli.commands.example.app.boot1;

import com.essaid.picocli.commands.ICommandManager2;
import com.essaid.picocli.commands.example.app.boot1.comp.PicocliCommandsRunner;
import com.essaid.picocli.commands.impl.CommandManager2Impl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppExample1Configuration {
  
  @Bean
  ICommandManager2 commandManager(){
    return new CommandManager2Impl();
  }
  
  @Bean
  PicocliCommandsRunner picocliCommandsRunner(){
    return new PicocliCommandsRunner();
  }
}
