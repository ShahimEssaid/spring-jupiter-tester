package com.essaid.pico.commands2.exception;

public class CommandsException extends IllegalStateException{
  public CommandsException() {
  }
  
  public CommandsException(String s) {
    super(s);
  }
  
  public CommandsException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public CommandsException(Throwable cause) {
    super(cause);
  }
}
