package com.essaid.util.intercept.group;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.InterceptorOrder;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.context.ListInterceptorContext;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;
import com.essaid.util.intercept.domain.Domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InterceptorList<D extends  Domain> extends AbstractInterceptorGroup<D> {
  
  private final InterceptorComparator INTERCEPTOR_COMPARATOR2 = new InterceptorComparator();
  
  private final CopyOnWriteArrayList<IInterceptor<? extends D>> interceptors = new CopyOnWriteArrayList<>();
  
  public InterceptorList(D domain, List<IInterceptor<? extends D>> interceptors) {
    super(domain);
    this.interceptors.addAll(interceptors);
    this.interceptors.sort(INTERCEPTOR_COMPARATOR2);
  }
  
  @Override
  protected IInterceptorContext doBuildInterceptorContext(AbstractInterceptorGroup<D> drcAbstractInterceptorGroup,
      IInterceptorContextGlobalData globalData, IInterceptorContextLocalData localData) {
    List<IInterceptor<D>> clone = (List<IInterceptor<D>>) interceptors.clone();
    clone.sort(INTERCEPTOR_COMPARATOR2);
    IInterceptorContext context = new ListInterceptorContext(this, globalData, localData,
        Collections.unmodifiableList(clone));
    return context;
  }
  
  @Override
  public List<IInterceptor<? extends D>> getInterceptors() {
    return Collections.unmodifiableList(interceptors);
  }
  
  @Override
  public void addInterceptor(IInterceptor interceptor) {
    synchronized (this.interceptors) {
      interceptors.add(interceptor);
      interceptors.sort(INTERCEPTOR_COMPARATOR2);
    }
  }
  
  @Override
  public boolean removeInterceptor(IInterceptor interceptor) {
    synchronized (this.interceptors) {
      return interceptors.remove(interceptor);
    }
  }
  
  private static class InterceptorComparator implements Comparator<IInterceptor> {
    
    @Override
    public int compare(IInterceptor o1, IInterceptor o2) {
      int firstOrder = o1.getClass().getAnnotation(InterceptorOrder.class) == null ? 0 : o1.getClass()
          .getAnnotation(InterceptorOrder.class)
          .value();
      
      int secondOrder = o2.getClass().getAnnotation(InterceptorOrder.class) == null ? 0 : o2.getClass()
          .getAnnotation(InterceptorOrder.class)
          .value();
      
      return firstOrder - secondOrder;
    }
  }
  
}
