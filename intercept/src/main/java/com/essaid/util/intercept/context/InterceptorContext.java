package com.essaid.util.intercept.context;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.group.IInterceptorGroup;
import com.essaid.util.intercept.data.IDomainData;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;

import java.util.Collection;
import java.util.List;

public class InterceptorContext<D extends IDomain, R extends Object, C extends IInterceptorContext<D, R, C>> implements IInterceptorContext<D, R, C>{
  
  
  @Override
  public IInterceptorGroup<D, R, C> getInterceptorGroup() {
    return null;
  }
  
  @Override
  public Collection<IInterceptor<D, R, C>> getInterceptors() {
    return null;
  }
  
  
  @Override
  public Object doNextInterceptor() {
    return null;
  }
  
  @Override
  public IInterceptorContextLocalData getLocalData() {
    return null;
  }
  
  @Override
  public IInterceptorContextGlobalData getGlobalData() {
    return null;
  }
  
  @Override
  public IDomainData getDomainData() {
    return null;
  }
}
