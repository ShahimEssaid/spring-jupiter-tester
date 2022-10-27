package com.essaid.util.modelproxy;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;

public interface IModelProxy<P extends IModelProxy<P>> extends ReadWriteLock {
  
  void configure(IModelProxyConfigurer<P> configurer);
  
  <I extends IModelInterface> I as(Class<I> cls);
  
  IModelProxyInternal<P> internal();
  
  interface IModelProxyInternal<P extends IModelProxy<P>> extends IModelProxy<P>, ReadWriteLock {
    
    void addHandler(Class<? extends IModelInvocationHandler<P>> handlerClass,
                    Class<? extends IModelProxy> proxyClass);
    
    Set<Class<? extends IModelInvocationHandler<P>>> getHandlers();
    
    Set<Class<? extends IModelInterface>> getInterfaces();
    
    Map<Object, Object> getData(Class<? extends IModelProxy> proxyClass);
    
  }
}
