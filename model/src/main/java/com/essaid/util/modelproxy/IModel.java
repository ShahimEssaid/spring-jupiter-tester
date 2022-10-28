package com.essaid.util.modelproxy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

public interface IModel extends IProxiable, ReadWriteLock {
  
  void appendConfiguration(IModelConfigurer configurer);
  
  void prependConfiguration(IModelConfigurer configurer);
  
  IModelInternal internal();
  
  interface IModelInternal extends IModel, ReadWriteLock {
    
    void addHandler(Class<? extends IModelInvocationHandler> handlerClass, Class<? extends IModel> proxyClass);
    
    List<Class<? extends IModelInvocationHandler>> getHandlers();
    
    List<Class<? extends IModelInterface>> getInterfaces();
    
    Map<Object, Object> getData(Class<? extends IModel> proxyClass);
    
  }
}
