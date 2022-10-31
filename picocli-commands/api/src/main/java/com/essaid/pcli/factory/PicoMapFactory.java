package com.essaid.pcli.factory;

import picocli.CommandLine;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class PicoMapFactory extends AbstractFactory {
  public PicoMapFactory(CommandLine.IFactory delegateFactory) {
    super(delegateFactory);
  }
  
  public PicoMapFactory() {
  }
  
  @Override
  public <K> K doCreate(Class<K> cls) {
    if (cls.isInterface()) {
      if (SortedMap.class.isAssignableFrom(cls)) {
        return cls.cast(new TreeMap<Object, Object>());
      }
      if (Map.class.isAssignableFrom(cls)) {
        return cls.cast(new LinkedHashMap<Object, Object>());
      }
    }
    return null;
  }
}
