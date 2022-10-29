package com.essaid.util.intercept.example;

import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.interceptor.IInterceptorOutcome;

public class SomeOtherInterceptor implements IInterceptor.IInterceptorInternal {
  
  
  @Override
  public String getId() {
    return null;
  }
  
  @Override
  public String getInstanceId() {
    return null;
  }
  
  @Override
  public IDomain getDomain() {
    return null;
  }
  
  @Override
  public IInterceptorOutcome intercept(IInterceptorContext interceptorContext) {
    return null;
  }
  
  @Override
  public IInterceptorInternal interceptorInternal() {
    return null;
  }
  
  @Override
  public void run() {
  
  }
  
  @Override
  public Object call() throws Exception {
    return null;
  }
}
