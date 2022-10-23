package com.essaid.util.intercept;

public interface IInterceptor<D extends IDomain, R extends Object, C extends IInterceptorContext> {
  R doInterceptor(C interceptorContext);
}
