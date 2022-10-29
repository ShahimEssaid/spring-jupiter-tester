package com.essaid.util.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

public interface IModel extends ReadWriteLock {
  
  <I extends IModelInterface> I as(Class<I> cls);
  
  IModelInternal internal();
  
  interface IModelInternal extends IModel, ReadWriteLock {
    
    boolean isPermissive();
    
    void setPermissable(boolean isPermissive);
    
    default void appendConfiguration(IModelConfigurer... configurer){
      appendConfiguration(Arrays.asList(configurer));
    }
    
    void appendConfiguration(List<IModelConfigurer> configurers);
    
    
    default void prependConfiguration(IModelConfigurer... configurer){
      prependConfiguration(Arrays.asList(configurer));
    }
    
    void prependConfiguration(List<IModelConfigurer> configurers);
    
    
    void addHandler(Class<? extends IModelInvocationHandler> handlerClass, Class<? extends IModel> modelClass,
                    boolean append);
    
    List<Class<? extends IModelInvocationHandler>> getHandlers();
    
    List<Class<? extends IModelInterface>> getInterfaces();
    
    Map<Object, Object> getData(Class<? extends IModel> proxyClass);
    
  }
}
