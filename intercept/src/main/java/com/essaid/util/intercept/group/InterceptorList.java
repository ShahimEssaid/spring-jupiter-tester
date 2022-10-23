package com.essaid.util.intercept.group;

import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.data.IInterceptorGroupData;
import com.essaid.util.intercept.group.InterceptorGroup;

import java.util.Collection;

public class InterceptorList<D extends IDomain, R extends Object, C extends IInterceptorContext<D, R, C>> extends InterceptorGroup<D, R, C> {
  @Override
  public R doInterceptor(IInterceptorContext<D, R, C> interceptorContext) {
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
