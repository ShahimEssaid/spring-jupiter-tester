package com.essaid.util.asserts;

import java.util.Arrays;

/**
 * Some comments
 */
public class Asserts {
  
  public static <T extends Object> T notNull(T object, String errorMessage) {
    if (object != null) {
      return object;
    } else {
      throw new NullPointerException(errorMessage);
    }
  }
  
  public static void allNotNull(String message, Object... objects) {
    for (int i = 0; i < objects.length; ++i) {
      if (objects[i] == null)
        throw new NullPointerException(message + " for arg num:" + i + " and args:" + Arrays.asList(objects));
    }
  }
}
