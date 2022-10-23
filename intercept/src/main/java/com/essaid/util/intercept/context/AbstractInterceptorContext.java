package com.essaid.util.intercept.context;

import com.essaid.util.asserts.Asserts;
import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;
import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.group.IInterceptorGroup;

import java.util.Collection;

public abstract class AbstractInterceptorContext implements IInterceptorContext {
  
  private final IInterceptorGroup group;
  private final IInterceptorContextGlobalData globalData;
  private final IInterceptorContextLocalData localData;
  private final Collection<IInterceptor> interceptors;
  
  public AbstractInterceptorContext(IInterceptorGroup group, IInterceptorContextGlobalData globalData,
                                    IInterceptorContextLocalData localData, Collection<IInterceptor> interceptors) {
    Asserts.notNull(group, "IInterceptorGroup can't be null while constructing InterceptorContext");
    Asserts.notNull(globalData, "IInterceptorContextGlobalData can't be null while constructing InterceptorContext");
    Asserts.notNull(localData, "IInterceptorContextLocalData can't be null while constructing InterceptorContext");
    this.group = group;
    this.globalData = globalData;
    this.localData = localData;
    this.interceptors = interceptors;
  }
  
  
  @Override
  final public IInterceptorGroup getInterceptorGroup() {
    return group;
  }
  
  @Override
  final public Collection<IInterceptor> getContextInterceptors() {
    return interceptors;
  }
  
  @Override
  final public Object doNextInterceptor() {
    IInterceptor nextInterceptor = getNextInterceptor();
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
  
  abstract protected IInterceptor getNextInterceptor();
  
}
