package com.essaid.util.intercept.context;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.group.IInterceptorGroup;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;

import java.util.Collection;

public interface IInterceptorContext {
  
  IInterceptorGroup getInterceptorGroup();
  
  Collection<IInterceptor> getContextInterceptors();
  
  Object doNextInterceptor();
  
  IInterceptorContextLocalData getLocalData();
  
  IInterceptorContextGlobalData getGlobalData();
  
}
