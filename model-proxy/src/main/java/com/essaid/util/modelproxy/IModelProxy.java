package com.essaid.util.modelproxy;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;

public interface IModelProxy extends IProxiable, ReadWriteLock {
  
  void configure(IModelProxyConfigurer configurer);
  
  IModelProxyInternal internal();
  
  interface IModelProxyInternal extends IModelProxy, ReadWriteLock {
    
    void addHandler(Class<? extends IModelInvocationHandler> handlerClass, Class<? extends IModelProxy> proxyClass);
    
    Set<Class<? extends IModelInvocationHandler>> getHandlers();
    
    Set<Class<? extends IModelInterface>> getInterfaces();
    
    Map<Object, Object> getData(Class<? extends IModelProxy> proxyClass);
    
  }
}
