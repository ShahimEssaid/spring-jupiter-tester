package com.essaid.picocli.commands.spring;

import com.essaid.picocli.commands.ICommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandsConfiguration {
  
  @Bean
  ICommands defaultCommands(@Value("${com.essaid.picocli.commands.commands_name:" + ICommands.DEFAULT_ICOMMANDS_NAME + "}") String commandsName) {
    System.out.println("========== COMMANDS NAME IS: " + commandsName);
    return ICommands.getOrCreateCommandsInstance(commandsName);
  }
}
