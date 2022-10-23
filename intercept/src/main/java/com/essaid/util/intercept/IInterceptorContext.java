package com.essaid.util.intercept;

import com.essaid.util.intercept.data.IDomainData;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;

import java.util.List;

public interface IInterceptorContext {
  
  IInterceptorGroup getInterceptorGroup();
  
  List<IInterceptor> getInterceptors();
  
  Object doNextInterceptor();
  
  IInterceptorContextLocalData getLocalData();
  
  IInterceptorContextGlobalData getGlobalData();
  
  IDomainData getDomainData();
  
}
