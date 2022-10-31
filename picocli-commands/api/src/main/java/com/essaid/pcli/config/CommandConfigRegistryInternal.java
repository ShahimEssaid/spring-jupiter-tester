package com.essaid.pcli.config;

import com.essaid.pcli.type.ICommandTypeRegistry;
import lombok.Getter;

import java.util.Map;

public class CommandConfigRegistryInternal implements ICommandConfigRegistry.ICommandConfigRegistryInternal {
  @Getter
  private final String name;
  
  public CommandConfigRegistryInternal(String name) {
    this.name = name;
  }
}
