package com.essaid.util.intercept.interceptor;

import com.essaid.util.intercept.context.IInterceptorContext;

public interface IInterceptorOutcome {
  
  IInterceptor getInterceptor();
  
  Object getResult();
  
  IInterceptorContext getContext();
  
}
