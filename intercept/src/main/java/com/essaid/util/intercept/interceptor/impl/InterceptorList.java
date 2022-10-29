package com.essaid.util.intercept.interceptor.impl;

import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.intercept.interceptor.IInterceptorList;
import com.essaid.util.intercept.interceptor.Interceptor;
import com.essaid.util.model.IModelConfigurer;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InterceptorList extends AbstractInterceptorGroup implements IInterceptorList,
    IInterceptor.IInterceptorInternal {
  
  private final InterceptorComparator INTERCEPTOR_COMPARATOR = new InterceptorComparator();
  
  private final CopyOnWriteArrayList<IInterceptor> interceptors = new CopyOnWriteArrayList<>();
  private final boolean isSorted;
  
  
  public InterceptorList(IDomain domain, List<IModelConfigurer> dataConfigurerOverrides,
                         Boolean overridePermissiveness, List<IModelConfigurer> localDataConfigurerOverrides,
                         Boolean overrideLocalPermissiveness, boolean isSorted) {
    super(domain, dataConfigurerOverrides, overridePermissiveness, localDataConfigurerOverrides,
        overrideLocalPermissiveness);
    this.isSorted = isSorted;
  }
  
  @Override
  public void addInterceptor(IInterceptor interceptor) {
    synchronized (this.interceptors) {
      interceptors.add(interceptor);
      interceptors.sort(INTERCEPTOR_COMPARATOR);
    }
  }
  
  @Override
  public void run() {
  
  }
  
  @Override
  public Object call() throws Exception {
    return null;
  }
  
  @Override
  public boolean isSorted() {
    return isSorted;
  }
  
  @Override
  public void addInterceptor(IInterceptor interceptor, int index) {
  
  }
  
  @Override
  public Iterator<IInterceptor> getIterator() {
    return interceptors.iterator();
  }
  
  @Override
  public IInterceptorInternal interceptorInternal() {
    return null;
  }
  
  
  private static class InterceptorComparator implements Comparator<IInterceptor> {
    
    @Override
    public int compare(IInterceptor o1, IInterceptor o2) {
      int firstOrder = o1.getClass().getAnnotation(Interceptor.class) == null ? 0 : o1.getClass()
          .getAnnotation(Interceptor.class)
          .value();
      
      int secondOrder = o2.getClass().getAnnotation(Interceptor.class) == null ? 0 : o2.getClass()
          .getAnnotation(Interceptor.class)
          .value();
      
      return firstOrder - secondOrder;
    }
  }
  
  
}
