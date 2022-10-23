package com.essaid.util.intercept.context;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;
import com.essaid.util.intercept.group.IInterceptorGroup;

import java.util.Iterator;
import java.util.List;

public class ListInterceptorContext extends AbstractInterceptorContext {
  
  private final Iterator<IInterceptor> iterator;
  
  public ListInterceptorContext(IInterceptorGroup group, IInterceptorContextGlobalData globalData,
                                IInterceptorContextLocalData localData, List<IInterceptor> interceptors) {
    super(group, globalData, localData, interceptors);
    this.iterator = interceptors.iterator();
  }
  
  @Override
  protected IInterceptor getNextInterceptor() {
    return iterator.hasNext() ? iterator.next() : null;
  }
}
