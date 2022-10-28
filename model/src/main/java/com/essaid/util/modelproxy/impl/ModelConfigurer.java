package com.essaid.util.modelproxy.impl;

import com.essaid.util.modelproxy.IModelInterface;
import com.essaid.util.modelproxy.IModelInvocationHandler;
import com.essaid.util.modelproxy.IModel;
import com.essaid.util.modelproxy.IModelConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class ModelConfigurer implements IModelConfigurer {
  
  private final String id;
  private final String name;
  private final String version;
  private final int priority;
  private final String shortDescription;
  private final String description;
  private final String notes;
  private final List<Class<? extends IModelInterface>> interfaceClasses;
  private final List<Class<? extends IModelInvocationHandler>> handlerClasses;
  private final Class<? extends IModel> proxyClass;
  
  public ModelConfigurer(String id, String name, String version, int priority, String shortDescription,
                         String description, String notes, Class<? extends IModelInterface>[] interfaces, Class<
      ? extends IModelInvocationHandler>[] handlers, Class<? extends IModel> proxyClass) {
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
  public void configure(IModel proxy, boolean append) {
    
    IModel.IModelInternal internal = proxy.internal();
    Lock lock = internal.writeLock();
    lock.lock();
    if (append) {
      
      internal.getInterfaces().addAll(interfaceClasses);
      
      for (Class<? extends IModelInvocationHandler> handlerClass : handlerClasses) {
        internal.addHandler(handlerClass, proxyClass);
      }
    } else {
      List<Class<? extends IModelInterface>> reversed = new ArrayList<>(interfaceClasses);
      Collections.reverse(reversed);
      reversed.forEach(aClass -> proxy.internal().getInterfaces().add(0, aClass));
      
      
      List<Class<? extends IModelInvocationHandler>> reversedHandlers = new ArrayList<>(handlerClasses);
      Collections.reverse(reversedHandlers);
      
      reversedHandlers.forEach(aClass -> proxy.internal().getHandlers().add(0, aClass));
      
      for (Class<? extends IModelInvocationHandler> handlerClass : reversedHandlers) {
        internal.addHandler(handlerClass, proxyClass);
      }
      //TODO: implement
      throw new UnsupportedOperationException("Not implemented yet.");
    }
    lock.unlock();
  }
}
