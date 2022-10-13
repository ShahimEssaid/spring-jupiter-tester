package com.essaid.pico.commands2;

import java.lang.reflect.Constructor;

public class Utils {
  
  void getConstructors(Class<?> clazz) {
    Constructor<?>[] constructors = clazz.getConstructors();
    
    for (Constructor constructor : constructors) {
      Class[] parameterTypes = constructor.getParameterTypes();
    }
  }
  
  
  
}
