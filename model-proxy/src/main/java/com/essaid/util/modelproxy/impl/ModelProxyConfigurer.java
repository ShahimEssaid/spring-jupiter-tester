package com.essaid.util.modelproxy.impl;

import com.essaid.util.modelproxy.IModelInterface;
import com.essaid.util.modelproxy.IModelInvocationHandler;
import com.essaid.util.modelproxy.IModelProxy;
import com.essaid.util.modelproxy.IModelProxyConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class ModelProxyConfigurer implements IModelProxyConfigurer {
  
  private final String id;
  private final String name;
  private final String version;
  private final int priority;
  private final String shortDescription;
  private final String description;
  private final String notes;
  private final List<Class<? extends IModelInterface>> interfaceClasses;
  private final List<Class<? extends IModelInvocationHandler>> handlerClasses;
  private final Class<? extends IModelProxy> proxyClass;
  
  public ModelProxyConfigurer(String id, String name, String version, int priority, String shortDescription,
                              String description, String notes, Class<? extends IModelInterface>[] interfaces, Class<
      ? extends IModelInvocationHandler>[] handlers, Class<? extends IModelProxy> proxyClass) {
    this.id = id;
    this.name = name;
    this.version = version;
    this.priority = priority;
    this.shortDescription = shortDescription;
    this.description = description;
    this.notes = notes;
    this.interfaceClasses = Arrays.asList(interfaces);
    this.handlerClasses = Arrays.asList(handlers);
    this.proxyClass = proxyClass;
  }
  
  @Override
  public String id() {
    return id;
  }
  
  @Override
  public String name() {
    return name;
  }
  
  @Override
  public String version() {
    return version;
  }
  
  @Override
  public int priority() {
    return priority;
  }
  
  @Override
  public String shortDescription() {
    return shortDescription;
  }
  
  @Override
  public String description() {
    return description;
  }
  
  @Override
  public String notes() {
    return notes;
  }
  
  @Override
  public void configure(IModelProxy proxy) {
    IModelProxy.IModelProxyInternal internal = proxy.internal();
    Lock lock = internal.writeLock();
    lock.lock();
  
    for(Class<? extends IModelInterface> cls : interfaceClasses){
        internal.getInterfaces().add(cls);
    }
    
    for(Class<? extends IModelInvocationHandler> handler: handlerClasses){
      internal.addHandler(handler,proxyClass);
    }
    lock.unlock();
  
  }
}
