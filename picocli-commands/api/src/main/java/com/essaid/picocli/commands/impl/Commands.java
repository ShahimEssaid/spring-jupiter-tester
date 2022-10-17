package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.Constants;
import com.essaid.picocli.commands.ICommandRegistry;
import com.essaid.picocli.commands.ICommandType;
import com.essaid.picocli.commands.ICommands;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Commands implements ICommands.IICommands {
  
  
  private final ICommandRegistry manager;
  private final Map<String, ICommandType> types;
  
  Commands(ICommandRegistry manager) {
    this.manager = manager;
    this.types = manager.internal()
        .getPathCommandTypes()
        .entrySet()
        .stream()
        .map(stringListEntry -> new CommandType(null, stringListEntry.getValue().get(0), this))
        .collect(Collectors.toMap(ICommandType::getPath, Function.identity()));

//    Set<String> paths = types.keySet();
//    for (String path : paths) {
//      if(path.startsWith(Constants.RESERVED_PATH_PREFIX)) continue;
//
//      int i = path.indexOf(".");
//      while (i < -1) {
//        String subpath = path.substring(0, i);
//        if (!types.containsKey(subpath)) {
//          ICommandType noOpTemplate = types.get(Constants.NO_OP_COMMAND_PATH_PREFIX);
//          ICommandType noOp = new CommandType(manager, subpath, noOpTemplate.getCommandClass(), noOpTemplate
//          .getQualifier(),
//              noOpTemplate.getFactory());
//          noOp = new CommandType(noOp, this);
//          types.put(subpath, noOp);
//        }
//        i = subpath.indexOf(".");
//      }
//
//    }
  }
  
  @Override
  public CommandLine getCommandLine() {
    return getCommandLine(null, null);
  }
  
  @Override
  public CommandLine getCommandLine(String rootCommandTemplatePath, String noOpCommandTemplatePath,
                                    String... subCommandPaths) {
    CommandLine rootCommandLine = rootCommandTemplatePath != null ?
        new CommandLine(types.get(rootCommandTemplatePath)) :
        new CommandLine(types.get(Constants.RESERVED_ROOT_COMMAND_PATH));
    
    List<String> pathPrefixes = Arrays.asList(subCommandPaths);
    if (pathPrefixes.isEmpty()) {
      pathPrefixes.add("");
    }
    
    Map<String, CommandLine> builtCommandLines = new TreeMap<>();
    
    types.values()
        .stream()
        .filter(commandType -> !commandType.getPath().startsWith(Constants.RESERVED_TEMPLATE_COMMAND_PREFIX))
        .forEach(commandType -> buildCommandLine(commandType.getPath(), commandType, pathPrefixes, builtCommandLines,
            noOpCommandTemplatePath != null ? noOpCommandTemplatePath : Constants.RESERVED_NO_OP_COMMAND_PATH));
    
    return rootCommandLine;
  }
  
  private CommandLine buildCommandLine(String path, ICommandType commandType, List<String> pathPrefixes, Map<String,
      CommandLine> builtCommandLines, String noOpCommandPath) {
    if (builtCommandLines.containsKey(path)) return builtCommandLines.get(path);
    if (!pathPrefixes.stream().filter(prefix -> path.startsWith(prefix)).findAny().isPresent()) return null;
    
    if (Objects.isNull(commandType)) {
      commandType = new CommandType(null, types.get(noOpCommandPath), this);
    }
    CommandLine commandLine = new CommandLine(commandType);
    builtCommandLines.put(path, commandLine);
    
    int index = path.indexOf(".");
    if (index != -1) {
      String parentPath = path.substring(0, index);
      CommandLine parent = buildCommandLine(parentPath, types.get(parentPath), pathPrefixes, builtCommandLines,
          noOpCommandPath);
      if (parent != null) {
      
      }
    }
  }
  
  
  @Override
  public IICommands internal() {
    return this;
  }
}
