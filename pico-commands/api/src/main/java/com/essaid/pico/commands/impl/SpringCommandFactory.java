package com.essaid.pico.commands.impl;

import com.essaid.pico.commands.PCommand;
import com.essaid.pico.commands.PCommandFactory;
import com.essaid.pico.commands.PCommandType;
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
