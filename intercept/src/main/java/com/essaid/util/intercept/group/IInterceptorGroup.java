package com.essaid.util.intercept.group;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.data.IInterceptorGroupData;
import com.essaid.util.intercept.domain.IDomain;

import java.util.Collection;

public interface IInterceptorGroup<D extends IDomain, R extends Object, C extends IInterceptorContext<D, R, C>> extends IInterceptor<D, R, C> {
  
  /**
   * Returns a snapshot of the {@link IInterceptor}'s list of {@link IInterceptor}s that can be modified without
   * affecting the chain's list.
   *
   * @return a snapshot of the {@link IInterceptor}'s list of {@link IInterceptor}s that can be modified without
   * affecting the chain's list.
   */
  Collection<IInterceptor<D, R, C>> getInterceptors();
  
  void addInterceptor(IInterceptor<D, R, C> interceptor);
  
  
  boolean removeInterceptor(IInterceptor<D, R, C> interceptor);
  
  IInterceptorGroupData getGroupData();
  
  D getDomain();
  
}
