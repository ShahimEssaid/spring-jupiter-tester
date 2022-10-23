package com.essaid.util.intercept.group;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;
import com.essaid.util.intercept.domain.IDomain;

import java.util.Collection;

public class InterceptorList<D extends IDomain, R extends Object, C extends IInterceptorContext<D, R, C>> extends AbstractInterceptorGroup<D, R, C> {
  
  
  public InterceptorList(D domain, Collection<IInterceptor<D, R, C>> interceptors) {
    super(domain);
  }
  
  @Override
  protected IInterceptorContext<D, R, C> doBuildInterceptorContext(AbstractInterceptorGroup<D, R, C> drcAbstractInterceptorGroup, IInterceptorContextGlobalData globalData, IInterceptorContextLocalData localData) {
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
}
