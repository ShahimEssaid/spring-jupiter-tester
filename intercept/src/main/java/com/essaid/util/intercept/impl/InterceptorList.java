package com.essaid.util.intercept.impl;

import com.essaid.util.intercept.IDomain;
import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.IInterceptorContext;
import com.essaid.util.intercept.data.IInterceptorGroupData;

import java.util.Collection;

public class InterceptorList<D extends IDomain, R extends Object, C extends IInterceptorContext> extends InterceptorGroup<D, R, C>{
  @Override
  public R doInterceptor(C interceptorContext) {
    return null;
  }
  
  @Override
  public Collection<IInterceptor<D, R, C>> getInterceptors() {
    return null;
  }
  
  @Override
  public void addInterceptor(IInterceptor<? extends D, ? extends R, ? extends C> interceptor) {
  
  }
  
  @Override
  public boolean removeInterceptor(IInterceptor<? extends D, ? extends R, ? extends C> interceptor) {
    return false;
  }
  
  @Override
  public IInterceptorGroupData getData() {
    return null;
  }
  
  @Override
  public D getDomain() {
    return null;
  }
}
