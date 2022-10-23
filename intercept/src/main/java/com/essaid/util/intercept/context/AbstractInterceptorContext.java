package com.essaid.util.intercept.context;

import com.essaid.util.asserts.Asserts;
import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;
import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.group.IInterceptorGroup;

import java.util.Collection;

public abstract class AbstractInterceptorContext<D extends IDomain, R extends Object, C extends IInterceptorContext<D, R, C>> implements IInterceptorContext<D, R, C> {
  
  private final IInterceptorGroup<D, R, C> group;
  private final IInterceptorContextGlobalData globalData;
  private final IInterceptorContextLocalData localData;
  private final Collection<IInterceptor<D, R, C>> interceptors;
  
  public AbstractInterceptorContext(IInterceptorGroup<D, R, C> group, IInterceptorContextGlobalData globalData,
                                    IInterceptorContextLocalData localData, Collection<IInterceptor<D, R, C>> interceptors) {
    Asserts.notNull(group, "IInterceptorGroup can't be null while constructing InterceptorContext");
    Asserts.notNull(globalData, "IInterceptorContextGlobalData can't be null while constructing InterceptorContext");
    Asserts.notNull(localData, "IInterceptorContextLocalData can't be null while constructing InterceptorContext");
    this.group = group;
    this.globalData = globalData;
    this.localData = localData;
    this.interceptors = interceptors;
  }
  
  
  @Override
  final public IInterceptorGroup<D, R, C> getInterceptorGroup() {
    return group;
  }
  
  @Override
  final public Collection<IInterceptor<D, R, C>> getContextInterceptors() {
    return interceptors;
  }
  
  @Override
  final public R doNextInterceptor() {
    IInterceptor<D, R, C> nextInterceptor = getNextInterceptor();
    return nextInterceptor == null ? null : nextInterceptor.doInterceptor(this);
  }
  
  @Override
  final public IInterceptorContextLocalData getLocalData() {
    return localData;
  }
  
  @Override
  final public IInterceptorContextGlobalData getGlobalData() {
    return globalData;
  }
  
  abstract protected IInterceptor<D, R, C> getNextInterceptor();
  
}
