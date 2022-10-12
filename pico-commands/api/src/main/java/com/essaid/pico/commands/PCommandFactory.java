package com.essaid.pico.commands;

import com.essaid.pico.commands.impl.DefaultPCommandFactory;
import com.essaid.pico.commands.impl.SpringCommandFactory;
import org.springframework.context.ConfigurableApplicationContext;
import picocli.CommandLine;

public interface PCommandFactory {
  
  static  PCommandFactory getDefaultFactory(){
    return  new DefaultPCommandFactory();
  }
  
  static PCommandFactory getSpringFactory(ConfigurableApplicationContext context){
    return new SpringCommandFactory(context);
  }

  PCommand createCommand(PCommandType commandType);

}
