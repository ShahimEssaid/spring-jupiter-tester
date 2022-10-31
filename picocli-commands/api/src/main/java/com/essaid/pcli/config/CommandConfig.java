package com.essaid.pcli.config;

import com.essaid.pcli.util.IRegistrable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CommandConfig implements ICommandConfig, IRegistrable {
  
  @Getter @Setter
  private String commandConfigId;
  @Getter @Setter
  private List<String> mixinCommandConfigIds;
  @Getter @Setter
  private List<CommandTypeConfig> commandTypeConfigs;
  @Getter @Setter
  private Object registrationKey;
  

}
