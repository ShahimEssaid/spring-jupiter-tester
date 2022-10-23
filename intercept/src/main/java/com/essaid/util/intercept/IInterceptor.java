package com.essaid.util.intercept;

import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.domain.IDomain;

public interface IInterceptor<D extends IDomain> {
  Object doInterceptor(IInterceptorContext interceptorContext);

  
}
