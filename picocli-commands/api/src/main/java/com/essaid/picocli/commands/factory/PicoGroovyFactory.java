package com.essaid.picocli.commands.factory;

import picocli.CommandLine;

import java.util.concurrent.Callable;

public class PicoGroovyFactory extends AbstractFactory {
  static Class<?> GROOVY_CLOSURE_CLASS = loadClosureClass();
  
  public PicoGroovyFactory(CommandLine.IFactory delegateFactory) {
    super(delegateFactory);
  }
  
  private static Class<?> loadClosureClass() {
    try {
      return Class.forName("groovy.lang.Closure");
    } catch (Exception ignored) {
      return null;
    }
  }
  
  @Override
  public  <K> K doCreate(Class<K> cls) throws Exception {
    if (GROOVY_CLOSURE_CLASS != null && GROOVY_CLOSURE_CLASS.isAssignableFrom(cls)) {
      Callable<?> callable = Callable.class.cast(cls.getConstructor(Object.class, Object.class)
          .newInstance(null, null));
      try {
        return (K) callable.call();
      } catch (Exception ex) {
        throw new CommandLine.InitializationException("Error in Groovy closure: " + ex);
      }
    }
    return null;
  }
}
