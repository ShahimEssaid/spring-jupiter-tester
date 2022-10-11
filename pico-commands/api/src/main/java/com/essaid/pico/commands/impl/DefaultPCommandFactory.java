package com.essaid.pico.commands.impl;

import com.essaid.pico.commands.PCommand;
import com.essaid.pico.commands.PCommandFactory;
import com.essaid.pico.commands.PCommandType;
import picocli.CommandLine;

import java.lang.reflect.InvocationTargetException;

public class DefaultPCommandFactory implements PCommandFactory {
  @Override
  public PCommand createCommand(PCommandType commandType) {
    try {
      return commandType.getType().getDeclaredConstructor().newInstance();
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
