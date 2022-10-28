package com.essaid.util.modelproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public interface IModelInvocationHandler {
  
  public InvocationResult doInvoke(Object proxy, Method method, Object[] args);
  
  class InvocationResult {
  
    private final boolean handled;
    private final Object result;
  
    public InvocationResult(boolean handled, Object result){
      this.handled = handled;
      this.result = result;
    }
    
    public boolean isHandled(){
      return  handled;
    }
    
    public Object getResult(){
      return result;
    }
    
  }
}
