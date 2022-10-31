package com.essaid.pcli.factory;

import picocli.CommandLine;

import java.lang.reflect.Constructor;

public class PicoInstanceFactory extends AbstractFactory {
  public PicoInstanceFactory(CommandLine.IFactory delegateFactory) {
    super(delegateFactory);
  }
  
  public PicoInstanceFactory() {
  }
  
  @Override
  public <K> K doCreate(Class<K> cls) throws Exception {
    
    try {
      @SuppressWarnings("deprecation") // Class.newInstance is deprecated in Java 9
      K result = cls.newInstance();
      return result;
    } catch (Exception ex) {
      // TODO log the error at debug level
      Constructor<K> constructor = cls.getDeclaredConstructor();
      try {
        return constructor.newInstance();
      } catch (IllegalAccessException iaex) {
        constructor.setAccessible(true);
        return constructor.newInstance();
      }
    }
  }
}
