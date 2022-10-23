package com.essaid.util.intercept.group;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.data.IInterceptorGroupData;

import com.essaid.util.intercept.domain.IDomain;

import java.util.Collection;

public interface IInterceptorGroup<D extends IDomain> extends IInterceptor<D> {
  
  /**
   * Returns a snapshot of the {@link IInterceptor}'s list of {@link IInterceptor}s that can be modified without
   * affecting the chain's list.
   *
   * @return a snapshot of the {@link IInterceptor}'s list of {@link IInterceptor}s that can be modified without
   * affecting the chain's list.
   */
  Collection<IInterceptor<? extends D>> getInterceptors();
  
  void addInterceptor(IInterceptor<? extends D> interceptor);
  
  
  boolean removeInterceptor(IInterceptor<? extends D> interceptor);
  
  IInterceptorGroupData getGroupData();
  
  IDomain getDomain();
  
}
