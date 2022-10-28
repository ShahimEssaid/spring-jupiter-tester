package com.essaid.util.modelproxy;

public class IModelInterfaceNotAvailableException extends IllegalArgumentException {
  public IModelInterfaceNotAvailableException() {
  }
  
  public IModelInterfaceNotAvailableException(String s) {
    super(s);
  }
  
  public IModelInterfaceNotAvailableException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public IModelInterfaceNotAvailableException(Throwable cause) {
    super(cause);
  }
}
