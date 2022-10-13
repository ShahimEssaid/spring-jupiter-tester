package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.PCommand;
import com.essaid.picocli.commands.PCommandFactory;
import com.essaid.picocli.commands.PCommandType;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringCommandFactory implements PCommandFactory {
  
  private final ConfigurableApplicationContext context;
  
  public SpringCommandFactory(ConfigurableApplicationContext context){
    this.context = context;
  }
  @Override
  public PCommand createCommand(PCommandType commandType) {
    PCommand bean = context.getBean(commandType.getType());
    return bean;
  }
}
