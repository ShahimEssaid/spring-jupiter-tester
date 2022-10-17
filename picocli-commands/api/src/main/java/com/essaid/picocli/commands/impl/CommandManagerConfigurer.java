package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.ICommandRegistry;
import com.essaid.picocli.commands.ICommandType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;

public class CommandManagerConfigurer implements BeanPostProcessor {
  
  private final List<ICommandType> commandTypes;
  
  public CommandManagerConfigurer(List<ICommandType> commandTypes){
    this.commandTypes = commandTypes;
  }
  
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if(bean instanceof ICommandRegistry){
      ICommandRegistry manager = (ICommandRegistry) bean;
      manager.addCommandTypes(commandTypes);
    }
    
    return bean;
  }
}
