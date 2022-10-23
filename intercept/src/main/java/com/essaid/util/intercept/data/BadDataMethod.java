package com.essaid.util.intercept.data;

import java.util.NoSuchElementException;

public class BadDataMethod extends UnsupportedOperationException {
  
  public BadDataMethod(String message) {
    super(message);
  }
}
