package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.ICommandManager;
import com.essaid.picocli.commands.ICommands;

import java.util.Properties;

public class Commands implements ICommands.IICommands {
  
  @Override
  public IICommands internal() {
    return this;
  }
}
