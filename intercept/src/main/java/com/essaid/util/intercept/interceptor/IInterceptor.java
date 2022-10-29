package com.essaid.util.intercept.interceptor;

import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.domain.IDomain;

import java.util.concurrent.Callable;

public interface IInterceptor extends Callable<Object>, Runnable {
  
  IDomain getDomain();
  
  IInterceptorOutcome intercept(IInterceptorContext interceptorContext);
  
  default IInterceptorInternal interceptorInternal() {
    return (IInterceptorInternal) this;
  }
  
  interface IInterceptorInternal extends IInterceptor {
    String getId();
    
    String getInstanceId();
    
    IInterceptorGroup getInterceptorGroup();
    
  }
}
