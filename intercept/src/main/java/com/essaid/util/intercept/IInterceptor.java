package com.essaid.util.intercept;

import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.domain.IDomain;

import java.util.concurrent.Callable;

public interface IInterceptor extends Callable<Object>, Runnable {
  Object doInterceptor(IInterceptorContext interceptorContext);

  
}
