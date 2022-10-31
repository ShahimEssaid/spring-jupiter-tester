package com.essaid.pcli.spring;

import com.essaid.pcli.type.ICommandTypeRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandsConfiguration {
  
  @Bean
  ICommandTypeRegistry defaultCommands(@Value("${com.essaid.picocli.commands.commands_name:" + ICommandTypeRegistry.DEFAULT_REGISTRY_NAME + "}") String commandsName) {
    System.out.println("========== COMMANDS NAME IS: " + commandsName);
    return ICommandTypeRegistry.getOrCreateRegistry(commandsName);
  }
}
