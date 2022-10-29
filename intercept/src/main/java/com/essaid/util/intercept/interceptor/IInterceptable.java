package com.essaid.util.intercept.interceptor;

public interface IInterceptable {
  
  boolean canAdd(IInterceptor interceptor);
  
  boolean addInterceptor(IInterceptor interceptor);
  
  boolean removeInterceptor(IInterceptor interceptor);
  
}
