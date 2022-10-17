package com.essaid.picocli.commands.example.app.boot1;

import com.essaid.picocli.commands.ICommandRegistry;
import com.essaid.picocli.commands.example.app.boot1.comp.PicocliCommandsRunner;
import com.essaid.picocli.commands.impl.CommandRegistryImpl;
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
