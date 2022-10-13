package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.PCommand;
import com.essaid.picocli.commands.PCommandFactory;
import com.essaid.picocli.commands.PCommandType;

import java.lang.reflect.InvocationTargetException;

public class DefaultPCommandFactory implements PCommandFactory {
  @Override
  public PCommand createCommand(PCommandType commandType) {
    try {
      PCommand command = commandType.getType().getConstructor().newInstance();
      command.internal().setPCommandType(commandType);
      return command;
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

}
