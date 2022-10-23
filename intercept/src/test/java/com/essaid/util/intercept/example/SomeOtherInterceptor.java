package com.essaid.util.intercept.example;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.IInterceptorContext;

public class SomeOtherInterceptor implements IInterceptor<OtherDomain, Object, IInterceptorContext> {
  @Override
  public Object doInterceptor(IInterceptorContext interceptorContext) {
    return null;
  }
}
