package com.essaid.util.modelproxy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

public interface IModel extends IInterfaceable, ReadWriteLock {
  

  
  IModelInternal internal();
  
  interface IModelInternal extends IModel, ReadWriteLock {
  
    void appendConfiguration(IModelConfigurer configurer);
  
    default void appendConfiguration(List<IModelConfigurer> configurerList){
      configurerList.forEach(this::appendConfiguration);
    }
  
    void prependConfiguration(IModelConfigurer configurer);
  
  
    default void prependConfiguration(List<IModelConfigurer> configurerList){
      configurerList.forEach(this::prependConfiguration);
    }
    
    void addHandler(Class<? extends IModelInvocationHandler> handlerClass, Class<? extends IModel> proxyClass ,
                    boolean append);
    
    List<Class<? extends IModelInvocationHandler>> getHandlers();
    
    List<Class<? extends IModelInterface>> getInterfaces();
    
    Map<Object, Object> getData(Class<? extends IModel> proxyClass);
    
  }
}
