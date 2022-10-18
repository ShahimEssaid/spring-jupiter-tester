package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.Constants;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@AutoConfiguration
public class SpringAutoConfiguration {
  
  @Bean
  @ConditionalOnMissingBean(name = "commandsRootPostProcessor")
  @AutoConfigureOrder
  BeanPostProcessor commandsRootPostProcessor() {
    CommandType commandType = new CommandType(null, Constants.RESERVED_ROOT_COMMAND_PATH, NoOpCommand.class, null,
        new CommandFactory(NoOpCommand.class, null, true));
    return new CommandManagePostProcessor(Collections.singletonList(commandType));
  }
}
