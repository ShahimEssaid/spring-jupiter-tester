package com.essaid.pcli.type;

import com.essaid.util.asserts.Asserts;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface ICommandTypeRegistry {
  
  String DEFAULT_REGISTRY_NAME = "_default";
  Map<String, ICommandTypeRegistry> INSTANCES = new ConcurrentHashMap<>();
  
  static ICommandTypeRegistry getDefaultRegistry() {
    return getOrCreateRegistry(DEFAULT_REGISTRY_NAME);
  }
  
  static ICommandTypeRegistry getRegistry(String registryName) {
    Asserts.notNull(registryName, "Can't get registry with null name.");
    return INSTANCES.get(registryName);
  }
  
  static ICommandTypeRegistry getOrCreateRegistry(String registryName) {
    return INSTANCES.computeIfAbsent(registryName, CommandTypeRegistry::new);
  }
  
  static ICommandTypeRegistry removeRegistry(String instanceName) {
    return INSTANCES.remove(instanceName);
  }
  
  String getName();
  
  void addCommandType(ICommandType commandType);
  
  /**
   * @param commandType the command type to find a direct parent command type for based on id values.
   * @return the direct parent if there is one, or null.
   */
  ICommandType getPrent(ICommandType commandType);
  
  /**
   * @param commandType the command type to retrieve any direct children of.
   * @return a list of direct children, found by id values, if there are any. Empty list otherwise.
   */
  List<ICommandType> getChildren(ICommandType commandType);
  
  Map<String, List<ICommandType>> getCommandTypes();

}

