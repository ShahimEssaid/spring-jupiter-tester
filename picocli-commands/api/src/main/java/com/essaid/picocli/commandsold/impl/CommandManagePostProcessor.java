package com.essaid.picocli.commandsold.impl;

import com.essaid.picocli.commandsold.ICommandRegistry;
import com.essaid.picocli.commandsold.ICommandType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandManagePostProcessor implements BeanPostProcessor {
  
  private final List<ICommandType> commandTypes;
  
  public CommandManagePostProcessor(List<ICommandType> commandTypes){
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
