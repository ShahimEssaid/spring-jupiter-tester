package com.essaid.util.intercept.hold;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

public class InterceptorFactories {
  
  private Map<String, IInterceptorFactory> interceptorFactoryMap = new ConcurrentHashMap<>();
  
  boolean hasFactory(String id){
    return interceptorFactoryMap.containsKey(id);
  }
  
  IInterceptorFactory getInterceptorFactory(String factoryId) {
    return interceptorFactoryMap.get(factoryId);
  }
  
  List<IInterceptorFactory> loadNewFactories() {
    synchronized (interceptorFactoryMap) {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      classLoader = classLoader == null ? InterceptorFactories.class.getClassLoader() : classLoader;
      return loadNewFactories(classLoader);
    }
  }
  
  List<IInterceptorFactory> loadNewFactories(ClassLoader classLoader) {
    synchronized (interceptorFactoryMap) {
      List<IInterceptorFactory> newFactories = new ArrayList<>();
      ServiceLoader<IInterceptorFactory> loader = ServiceLoader.load(IInterceptorFactory.class);
      loader.forEach(iInterceptorFactory -> {
        String id = iInterceptorFactory.getInterceptorId();
        if (!interceptorFactoryMap.containsKey(id)) {
          interceptorFactoryMap.put(id, iInterceptorFactory);
          newFactories.add(iInterceptorFactory);
        }
      });
      return newFactories;
    }
  }
  
  
}
