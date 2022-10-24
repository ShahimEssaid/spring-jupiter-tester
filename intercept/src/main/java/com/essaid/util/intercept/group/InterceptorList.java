package com.essaid.util.intercept.group;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.Interceptor;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.context.ListInterceptorContext;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;
import com.essaid.util.intercept.domain.IDomain;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InterceptorList extends AbstractInterceptorGroup {
  
  private final InterceptorComparator INTERCEPTOR_COMPARATOR2 = new InterceptorComparator();
  
  private final CopyOnWriteArrayList<IInterceptor> interceptors = new CopyOnWriteArrayList<>();
  
  public InterceptorList(IDomain domain, List<IInterceptor> interceptors) {
    super(domain);
    this.interceptors.addAll(interceptors);
    this.interceptors.sort(INTERCEPTOR_COMPARATOR2);
  }
  
  @Override
  protected IInterceptorContext doBuildInterceptorContext(AbstractInterceptorGroup drcAbstractInterceptorGroup,
      IInterceptorContextGlobalData globalData, IInterceptorContextLocalData localData) {
    List<IInterceptor> clone = (List<IInterceptor>) interceptors.clone();
    clone.sort(INTERCEPTOR_COMPARATOR2);
    IInterceptorContext context = new ListInterceptorContext(this, globalData, localData,
        Collections.unmodifiableList(clone));
    return context;
  }
  
  @Override
  public List<IInterceptor> getInterceptors() {
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
  
  @Override
  public Collection<IInterceptorGroup> getDirectSubgroups() {
    return null;
  }
  
  @Override
  public Collection<IInterceptorGroup> getDirectInterceptors() {
    return null;
  }
  
  @Override
  public Collection<IInterceptorGroup> getAllSubgroups() {
    return null;
  }
  
  @Override
  public Collection<IInterceptorGroup> getAllInterceptors() {
    return null;
  }
  
  @Override
  public void run() {
  
  }
  
  @Override
  public Object call() throws Exception {
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
