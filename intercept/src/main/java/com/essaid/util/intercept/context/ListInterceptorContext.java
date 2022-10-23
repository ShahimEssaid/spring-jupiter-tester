package com.essaid.util.intercept.context;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;
import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.group.IInterceptorGroup;

import java.util.Iterator;
import java.util.List;

public class ListInterceptorContext<D extends IDomain, R extends Object, C extends IInterceptorContext<D, R, C>> extends AbstractInterceptorContext<D, R, C> {
  
  private final Iterator<IInterceptor<D, R, C>> iterator;
  
  public ListInterceptorContext(IInterceptorGroup<D, R, C> group, IInterceptorContextGlobalData globalData,
                                IInterceptorContextLocalData localData, List<IInterceptor<D, R, C>> interceptors) {
    super(group, globalData, localData, interceptors);
    this.iterator = interceptors.iterator();
  }
  
  @Override
  protected IInterceptor<D, R, C> getNextInterceptor() {
    return iterator.hasNext() ? iterator.next() : null;
  }
}
