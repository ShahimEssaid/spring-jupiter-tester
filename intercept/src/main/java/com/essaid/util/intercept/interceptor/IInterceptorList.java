package com.essaid.util.intercept.interceptor;

import java.util.Iterator;
import java.util.List;

public interface IInterceptorList extends IInterceptorGroup {
  
  boolean isSorted();
  
  void addInterceptor(IInterceptor interceptor);
  
  default void addInterceptors(List<IInterceptor> interceptors) {
    interceptors.forEach(this::addInterceptor);
  }
  
  void addInterceptor(IInterceptor interceptor, int index);
  
  Iterator<IInterceptor> getIterator();
}
