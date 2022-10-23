package com.essaid.util.asserts;

public class Asserts {
  
  public static <T extends Object> T notNull(T object, String errorMessage) {
    if (object != null) {
      return object;
    } else {
      throw new NullPointerException(errorMessage);
    }
  }
}
