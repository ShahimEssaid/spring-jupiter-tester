package com.essaid.picocli.commandsold.impl;

import java.util.Objects;

public class CommandsAsserts {
  
  public static void notNull(Object object, String errorMessage){
    if(Objects.isNull(object)) {
      NullPointerException nullPointerException = new NullPointerException();
    }
      throw new NullPointerException(errorMessage);
  }
}
