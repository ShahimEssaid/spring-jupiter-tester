package com.essaid.picocli.commandsold.old.example.app.boot1;

import com.essaid.picocli.commandsold.ICommandRegistry;
import com.essaid.picocli.commandsold.old.example.app.boot1.comp.PicocliCommandsRunner;
import com.essaid.picocli.commandsold.impl.CommandRegistryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppExample1Configuration {
  
  @Bean
  ICommandRegistry commandManager(){
    return new CommandRegistryImpl();
  }
  
  @Bean
  PicocliCommandsRunner picocliCommandsRunner(){
    return new PicocliCommandsRunner();
  }
}
