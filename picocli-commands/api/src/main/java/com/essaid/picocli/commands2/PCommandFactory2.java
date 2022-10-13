package com.essaid.picocli.commands2;

import picocli.CommandLine;

import java.util.Map;

public interface PCommandFactory2 extends CommandLine.IFactory {
//  <T extends  PCommand2> T create(PCommandType2 commandType2, Object ...args) throws InvocationTargetException, InstantiationException, IllegalAccessException;
  Map<Object, Object> getContext();
}
