package com.essaid.util.intercept.group;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.data.IInterceptorGroupData;
import com.essaid.util.intercept.domain.IDomain;

import java.util.Collection;

public interface IInterceptorGroup extends IInterceptor {
  
  /**
   * Returns a snapshot of the {@link IInterceptor}'s list of {@link IInterceptor}s that can be modified without
   * affecting the chain's list.
   *
   * @return a snapshot of the {@link IInterceptor}'s list of {@link IInterceptor}s that can be modified without
   * affecting the chain's list.
   */
  Collection<IInterceptor> getInterceptors();
  
  void addInterceptor(IInterceptor interceptor);
  
  
  boolean removeInterceptor(IInterceptor interceptor);
  
  IInterceptorGroupData getGroupData();
  
  IDomain getDomain();
  
  Collection<IInterceptorGroup> getDirectSubgroups();
  
  Collection<IInterceptorGroup> getDirectInterceptors();
  
  Collection<IInterceptorGroup> getAllSubgroups();
  
  Collection<IInterceptorGroup> getAllInterceptors();
  
  
}
