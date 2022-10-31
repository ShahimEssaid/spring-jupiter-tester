package com.essaid.pcli.config;

import java.util.List;

public interface ICommandConfig {
  
  String getCommandConfigId();
  
  List<String> getMixinCommandConfigIds();
  
  /**
   * Some javadoc
   * @return
   */
  List<CommandTypeConfig> getCommandTypeConfigs();
}
