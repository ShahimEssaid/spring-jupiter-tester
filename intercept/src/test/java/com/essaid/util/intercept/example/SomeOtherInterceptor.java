package com.essaid.util.intercept.example;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.context.IInterceptorContext;

public class SomeOtherInterceptor implements IInterceptor<OtherDomain, Object, IInterceptorContext> {
  @Override
  public Object doInterceptor(IInterceptorContext interceptorContext) {
    interceptorContext.getInterceptorGroup().addInterceptor(new SomeOtherInterceptor());
    return null;
  }
}
