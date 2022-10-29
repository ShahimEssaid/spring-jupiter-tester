package com.essaid.picocli.commands.util;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import picocli.CommandLine;

import java.lang.reflect.InvocationTargetException;

public class Reflect {
  
  
  public static Object[] invokeCommandSpec_commandMethodParamValues(CommandLine.Model.CommandSpec commandSpec) {
    try {
      return (Object[]) MethodUtils.invokeMethod(commandSpec, true, "commandMethodParamValues");
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static CommandLine.IFactory getCommandLine_factory(CommandLine commandLine)  {
    try {
      return (CommandLine.IFactory) FieldUtils.readField(commandLine, "factory", true);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static CommandLine.Help.Ansi getCommandLine_Help_ColorScheme_ansi(CommandLine.Help.ColorScheme colorScheme)  {
    try {
      return (CommandLine.Help.Ansi) FieldUtils.readField(colorScheme, "ansi", true);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
  
}
