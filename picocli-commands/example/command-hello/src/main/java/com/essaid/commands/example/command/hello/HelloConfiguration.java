package com.essaid.commands.example.command.hello;

import com.essaid.picocli.commands.ICommandFactory;
import com.essaid.picocli.commands.ICommandRegistry;
import com.essaid.picocli.commands.ICommandType;
import com.essaid.picocli.commands.impl.CommandManagerConfigurer;
import com.essaid.picocli.commands.impl.CommandType;
import com.essaid.picocli.commands.impl.SpringCommandFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@AutoConfiguration
@ConditionalOnBean(ICommandRegistry.class)
public class HelloConfiguration {
  
  @Bean
  String helloString() {
    System.out.println("=================== com.essaid.commands.example.command.hello.HelloConfiguration.helloString "
        + "called.");
    return "Hello";
  }
  
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Bean
  BeanPostProcessor helloCommandConfigurer(ConfigurableApplicationContext context, ICommandRegistry commandManager) {
    ICommandType helloCommandType = new CommandType(commandManager, "hello", HelloCommand.class, null, null);
    ICommandFactory factory = new SpringCommandFactory(helloCommandType.getCommandClass(), null, true, context);
    CommandManagerConfigurer commandManagerConfigurer =
        new CommandManagerConfigurer(Collections.singletonList(helloCommandType));
    return commandManagerConfigurer;
  
  }
  
}
