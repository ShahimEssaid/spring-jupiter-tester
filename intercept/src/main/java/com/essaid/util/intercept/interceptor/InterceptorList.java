package com.essaid.util.intercept.interceptor;

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

public class InterceptorList extends AbstractInterceptorGroup implements IInterceptorList {
  
  private final InterceptorComparator INTERCEPTOR_COMPARATOR2 = new InterceptorComparator();
  
  private final CopyOnWriteArrayList<IInterceptor> interceptors = new CopyOnWriteArrayList<>();
  private final boolean sorted;
  
  public InterceptorList(IDomain domain, List<IInterceptor> interceptors, boolean sorted) {
    super(domain);
    this.interceptors.addAll(interceptors);
    this.interceptors.sort(INTERCEPTOR_COMPARATOR2);
    this.sorted = sorted;
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
  public IInterceptorGroup getParentGroup() {
    return null;
  }
  
  @Override
  public void setParentGroup(IInterceptorGroup parent) {
  
  }
  
  @Override
  public void run() {
  
  }
  
  @Override
  public Object call() throws Exception {
    return null;
  }
  
  @Override
  public String getId() {
    return null;
  }
  
  @Override
  public String getInstanceId() {
    return null;
  }
  
  @Override
  public boolean isSorted() {
    return false;
  }
  
  @Override
  public void addInterceptorFirst(IInterceptor interceptor) {
  
  }
  
  @Override
  public void addInterceptorLast(IInterceptor interceptor) {
  
  }
  
  @Override
  public void addInterceptor(IInterceptor interceptor, int index) {
  
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
