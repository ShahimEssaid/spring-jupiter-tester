package com.essaid.picocli.commands;

import com.essaid.picocli.commands.impl.DefaultPCommandFactory;
import com.essaid.picocli.commands.impl.SpringCommandFactory;
import org.springframework.context.ConfigurableApplicationContext;

public interface PCommandFactory {
  
  static  PCommandFactory getDefaultFactory(){
    return  new DefaultPCommandFactory();
  }
  
  static PCommandFactory getSpringFactory(ConfigurableApplicationContext context){
    return new SpringCommandFactory(context);
  }

  PCommand createCommand(PCommandType commandType);

}
