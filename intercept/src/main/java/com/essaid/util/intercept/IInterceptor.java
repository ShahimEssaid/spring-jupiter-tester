package com.essaid.util.intercept;

import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.domain.Domain;

public interface IInterceptor<D extends Domain> {
  Object doInterceptor(IInterceptorContext interceptorContext);

  
}
