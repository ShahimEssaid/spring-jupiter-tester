package com.essaid.util.intercept;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AbstractInterceptorGroup<D extends IDomain, R extends Object, C extends IInterceptorContext> implements IInterceptorGroup<D, R, C> {
  
  private static InterceptorComparator INTERCEPTOR_COMPARATOR2 = new InterceptorComparator();
  private final CopyOnWriteArrayList<IInterceptor<? extends D, ? extends R, ? extends C>> interceptors =
      new CopyOnWriteArrayList<>();
  private volatile Data data;
  
  @Override
  final public R doInterceptor(IInterceptorContext interceptorContext) {
    
    IInterceptorData data = deriveData(interceptorContext);
    if (data == null) {
      data = createData();
    }
    
    C context = createContext(data);
    
    invokeContext(context);
    
    return getReturnValue(context);
  }
  
  abstract protected R getReturnValue(C newContext);
  
  protected void invokeContext(C newContext) {
  
    IInterceptorGroupData<T> data1 = getData();
    newContext.doNextInterceptor();
  }
  
  abstract protected C createContext(IInterceptorData data);
  
  protected IInterceptorData createData() {
    return new Data();
  }
  
  protected IInterceptorData deriveData(IInterceptorContext interceptorContext) {
    return interceptorContext.getData(IInterceptorData.class);
  }
  
  
  @Override
  public void addInterceptor(IInterceptor<? extends D,? extends R, ? extends C> interceptor) {
    synchronized (interceptors) {
      interceptors.add(interceptor);
      interceptors.sort(INTERCEPTOR_COMPARATOR2);
    }
  }
  
  @Override
  public boolean removeInterceptor(IInterceptor interceptor) {
    synchronized (interceptors) {
      return interceptors.remove(interceptor);
    }
  }
  
  private static class InterceptorComparator<T extends IInterceptor<?, ?, ? >> implements Comparator<T> {
    @Override
    public int compare(T first, T second) {
      int firstOrder = first.getClass().getAnnotation(InterceptorOrder.class) == null ? 0 : first.getClass()
          .getAnnotation(InterceptorOrder.class)
          .value();
      
      int secondOrder = second.getClass().getAnnotation(InterceptorOrder.class) == null ? 0 : second.getClass()
          .getAnnotation(InterceptorOrder.class)
          .value();
      
      return firstOrder - secondOrder;
    }
  }
}
