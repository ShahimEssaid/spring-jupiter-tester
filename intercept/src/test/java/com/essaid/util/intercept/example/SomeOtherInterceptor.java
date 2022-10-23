package com.essaid.util.intercept.example;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.context.IInterceptorContext;

import java.util.Collection;

public class SomeOtherInterceptor<D extends OtherDomain, R extends Object, C extends IInterceptorContext<D, R, C>> implements IInterceptor<D, R, C> {
  
  
  @Override
  public R doInterceptor(IInterceptorContext<D, R, C> interceptorContext) {
    return null;
  }
  
}
