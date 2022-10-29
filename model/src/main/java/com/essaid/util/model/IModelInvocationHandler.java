package com.essaid.util.model;

import java.lang.reflect.Method;

public interface IModelInvocationHandler {
  
  InvocationResult UN_HANDLED = new InvocationResult(false, null);
  
  InvocationResult doInvoke(Object proxy, Method method, Object[] args) throws Throwable;
  
  class InvocationResult {
    
    private final boolean handled;
    private final Object result;
    
    public InvocationResult(boolean handled, Object result) {
      this.handled = handled;
      this.result = result;
    }
    
    public boolean isHandled() {
      return handled;
    }
    
    public Object getResult() {
      return result;
    }
    
  }
}
