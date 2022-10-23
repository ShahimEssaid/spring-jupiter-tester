package com.essaid.util.intercept;

import com.essaid.util.intercept.data.IInterceptorGroupData;

import java.util.Collection;

public interface IInterceptorGroup<D extends IDomain, R extends Object, C extends IInterceptorContext> extends IInterceptor<D, R, C> {
  
  /**
   * Returns a snapshot of the {@link IInterceptor}'s list of {@link IInterceptor}s that can be modified without
   * affecting the chain's list.
   *
   * @return a snapshot of the {@link IInterceptor}'s list of {@link IInterceptor}s that can be modified without
   * affecting the chain's list.
   */
  Collection<IInterceptor<D, R, C>> getInterceptors();
  
  void addInterceptor(IInterceptor<? extends D, ? extends R, ? extends C> interceptor);
  
  
  boolean removeInterceptor(IInterceptor<? extends D, ? extends R, ? extends C> interceptor);
  
  IInterceptorGroupData getData();
  
  D getDomain();
}
