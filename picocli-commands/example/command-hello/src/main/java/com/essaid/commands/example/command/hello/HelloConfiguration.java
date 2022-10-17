package com.essaid.commands.example.command.hello;

import com.essaid.picocli.commands.ICommandManager2;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnBean(ICommandManager2.class)
public class HelloConfiguration {
  
  @Bean
  String helloString() {
    System.out.println("=================== com.essaid.commands.example.command.hello.HelloConfiguration.helloString "
        + "called.");
    Hello.main(new String[]{});
    return "Hello";
  }
  
}
