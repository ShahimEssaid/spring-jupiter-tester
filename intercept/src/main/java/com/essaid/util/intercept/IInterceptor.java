package com.essaid.util.intercept;

import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.domain.IDomain;

import java.util.Collection;

public interface IInterceptor {
  Object doInterceptor(IInterceptorContext interceptorContext);

  
}
