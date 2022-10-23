package com.essaid.util.intercept.data;

public class NonExistingValueException extends  IllegalStateException{
  public NonExistingValueException(String s) {
    super(s);
  }
}
