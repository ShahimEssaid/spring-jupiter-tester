package com.essaid.pcli.config;

import com.essaid.pcli.type.ICommandTypeRegistry;
import com.essaid.util.asserts.Asserts;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface ICommandConfigRegistry {
  static ICommandConfigRegistry getDefaultRegistry() {
    return getOrCreateRegistry(ICommandTypeRegistry.DEFAULT_REGISTRY_NAME);
  }
  
  static ICommandConfigRegistry getRegistry(String registryName) {
    Asserts.notNull(registryName, "Can't get registry with null name.");
    return ICommandConfigRegistry.ICommandConfigRegistryInternal.INSTANCES.get(registryName);
  }
  
  static ICommandConfigRegistry getOrCreateRegistry(String registryName) {
    return ICommandConfigRegistry.ICommandConfigRegistryInternal.INSTANCES.computeIfAbsent(registryName, CommandConfigRegistryInternal::new);
  }
  
  static ICommandConfigRegistry removeRegistry(String instanceName) {
    return ICommandConfigRegistry.ICommandConfigRegistryInternal.INSTANCES.remove(instanceName);
  }
  
  
  public interface ICommandConfigRegistryInternal extends ICommandConfigRegistry {
    Map<String, ICommandConfigRegistry.ICommandConfigRegistryInternal> INSTANCES = new ConcurrentHashMap<>();
  }
  
  
}
