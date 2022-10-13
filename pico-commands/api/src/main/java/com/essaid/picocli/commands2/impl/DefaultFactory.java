package com.essaid.picocli.commands2.impl;

import com.essaid.picocli.commands2.PCommandType2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Map;

public class DefaultFactory extends AbstractFactory {
  
  private static Logger logger = LoggerFactory.getLogger(DefaultFactory.class);

//
//  public DefaultFactory(PCommandType2 commandType2, Object... args) {
//    this(commandType2, new HashMap<>(), args);
//  }
  
  
  public DefaultFactory(PCommandType2 commandType2, Map<Object, Object> context, Object... args) {
    super(commandType2, context, args);
  }
  
  @Override
  public <K> K create(Class<K> cls) throws Exception {
    Object object = null;
    
    
    if (cls != getCommandType().getCommandLineClass()) {
      object = getPicoDefaultFactory().create(cls);
    } else {
      Object[] args = getArgs();
      logger.info("Creating command line for class: " + getCommandType().getCommandLineClass() + " with args: " + args);
      Constructor<?>[] constructors1 = cls.getConstructors();
      constructor:
      for (Constructor constructor : constructors1) {
        Parameter[] parameters = constructor.getParameters();
        if (args.length != parameters.length) continue constructor;
        for (int i = 0; i < parameters.length; ++i) {
          Class<?> parameterType = parameters[i].getType();
          if (parameterType.isAssignableFrom(args[i].getClass())) continue constructor;
        }
        logger.debug("Found constructor: " + constructor);
        object = constructor.newInstance(args);
      }
    }
    return (K) object;
  }
  
}
