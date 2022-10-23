package com.essaid.util.intercept;

import com.essaid.util.intercept.data.IDomainData;
import com.essaid.util.intercept.data.IInterceptorContextData;

import java.util.List;

public interface IInterceptorContext  {
  
  IInterceptorGroup getInterceptorGroup();
  
  List<IInterceptor> getInterceptors();
  
  Object doNextInterceptor();
  
  IInterceptorContextData getContextData();
  
  IDomainData getDomainData();
  
}
