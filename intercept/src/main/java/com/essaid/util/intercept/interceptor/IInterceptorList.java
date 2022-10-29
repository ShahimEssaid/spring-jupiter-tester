package com.essaid.util.intercept.interceptor;

import java.util.Iterator;

public interface IInterceptorList extends IInterceptorGroup {
  
  boolean isSorted();
  
  void addInterceptor(IInterceptor interceptor);
  
  void addInterceptor(IInterceptor interceptor, int index);
  
  Iterator<IInterceptor> getIterator();
}
