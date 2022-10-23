package com.essaid.util.intercept.group;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.InterceptorOrder;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.context.ListInterceptorContext;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;
import com.essaid.util.intercept.domain.IDomain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InterceptorList<D extends IDomain, R extends Object, C extends IInterceptorContext<D, R, C>> extends AbstractInterceptorGroup<D, R, C> {
  
  private final InterceptorComparator<D, R, C> INTERCEPTOR_COMPARATOR2 = new InterceptorComparator<>();
  
  private final CopyOnWriteArrayList<IInterceptor<D, R, C>> interceptors = new CopyOnWriteArrayList<>();
  
  public InterceptorList(D domain, List<IInterceptor<D, R, C>> interceptors) {
    super(domain);
    this.interceptors.addAll(interceptors);
    this.interceptors.sort(INTERCEPTOR_COMPARATOR2);
  }
  
  @Override
  protected IInterceptorContext<D, R, C> doBuildInterceptorContext(AbstractInterceptorGroup<D, R, C> drcAbstractInterceptorGroup, IInterceptorContextGlobalData globalData, IInterceptorContextLocalData localData) {
    List<IInterceptor<D, R, C>> clone = (List<IInterceptor<D, R, C>>) interceptors.clone();
    clone.sort(INTERCEPTOR_COMPARATOR2);
    IInterceptorContext<D, R, C> context = new ListInterceptorContext<>(this, globalData, localData,
        Collections.unmodifiableList(clone));
    return context;
  }
  
  @Override
  public List<IInterceptor<D, R, C>> getInterceptors() {
    return Collections.unmodifiableList(interceptors);
  }
  
  @Override
  public void addInterceptor(IInterceptor<D, R, C> interceptor) {
    synchronized (this.interceptors) {
      interceptors.add(interceptor);
      interceptors.sort(INTERCEPTOR_COMPARATOR2);
    }
  }
  
  @Override
  public boolean removeInterceptor(IInterceptor<D, R, C> interceptor) {
    synchronized (this.interceptors) {
      return interceptors.remove(interceptor);
    }
  }
  
  private static class InterceptorComparator<D extends IDomain, R extends Object, C extends IInterceptorContext<D, R,
      C>> implements Comparator<IInterceptor<D, R, C>> {
    
    @Override
    public int compare(IInterceptor<D, R, C> o1, IInterceptor<D, R, C> o2) {
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
