package com.essaid.util.intercept.context;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.group.IInterceptorGroup;
import com.essaid.util.intercept.data.IDomainData;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;

import java.util.Collection;

public interface IInterceptorContext<D extends IDomain, R extends Object, C extends IInterceptorContext<D, R, C>> {
  
  IInterceptorGroup<D, R, C> getInterceptorGroup();
  
  Collection<IInterceptor<D, R, C>> getContextInterceptors();
  
  Object doNextInterceptor();
  
  IInterceptorContextLocalData getLocalData();
  
  IInterceptorContextGlobalData getGlobalData();
  
}
