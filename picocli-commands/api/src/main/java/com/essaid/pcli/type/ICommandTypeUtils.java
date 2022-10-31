package com.essaid.pcli.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public class ICommandTypeUtils {
  
  public static Map<ICommandTypeRegistrant, List<ICommandType>> registerFromClassLoader(ICommandTypeRegistry registry
      , ClassLoader classLoader) {
    Map<ICommandTypeRegistrant, List<ICommandType>> loaded = new HashMap<>();
    ServiceLoader<ICommandTypeRegistrant> loader = ServiceLoader.load(ICommandTypeRegistrant.class, classLoader);
    loader.forEach(registrant -> {
      List<ICommandType> registrations = registrant.register(registry);
      loaded.put(registrant, registrations);
    });
    return loaded;
  }
}
