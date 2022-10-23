package com.essaid.util.intercept.tmp;

import com.essaid.util.intercept.AbstractInterceptorGroup;
import com.essaid.util.intercept.IInterceptorGroup;
import com.essaid.util.intercept.IInterceptorContext;
import com.essaid.util.intercept.InterceptorContext;

public class TestChain {
  
  public static void main(String[] args) {
    IInterceptorGroup<String, IInterceptorContext> chain = new AbstractInterceptorGroup<String, IInterceptorContext>() {
      @Override
      protected String getReturnValue(IInterceptorContext newContext) {
        return "hello";
      }
      
      @Override
      protected IInterceptorContext createContext(IInterceptorData data) {
        return new InterceptorContext(this, getInterceptors(), null);
      }
    };
  }
}
