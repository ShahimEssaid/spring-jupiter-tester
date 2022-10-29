package com.essaid.util.intercept.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public interface IInterceptorList extends IInterceptorGroup {
  
  boolean isSorted();
  
  default void addInterceptor(IInterceptor... interceptors) {
    addInterceptors(new ArrayList<>(Arrays.asList(interceptors)));
  }
  
  void addInterceptors(List<IInterceptor> interceptors);
  
  void addInterceptor(IInterceptor interceptor, int index);
  
  Iterator<IInterceptor> getIterator();
}
