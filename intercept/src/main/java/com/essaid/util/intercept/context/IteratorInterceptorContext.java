package com.essaid.util.intercept.context;

import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.model.IModel;

import java.util.Iterator;

public class IteratorInterceptorContext extends AbstractInterceptorContext {
  private final Iterator<IInterceptor> iterator;
  
  public IteratorInterceptorContext(IDomain domain, IModel data, IModel localData,
                                    Iterator<IInterceptor> interceptorIterator) {
    super(domain, data, localData);
    this.iterator = interceptorIterator;
  }
  
  @Override
  protected IInterceptor getNextInterceptor() {
    if (iterator.hasNext()) return iterator.next();
    return null;
  }
}
