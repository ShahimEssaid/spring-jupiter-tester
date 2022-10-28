package com.essaid.util.modelproxy.impl;

import com.essaid.util.concurrent.ReentrantReadWriteLockEx;
import com.essaid.util.modelproxy.IModelInterface;
import com.essaid.util.modelproxy.IModelInterfaceNotAvailableException;
import com.essaid.util.modelproxy.IModelInvocationHandler;
import com.essaid.util.modelproxy.IModelProxy;
import com.essaid.util.modelproxy.IModelProxyConfigurer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ModelProxy implements IModelProxy.IModelProxyInternal, InvocationHandler {
  
  private final Map<Object, Object> data = new ConcurrentHashMap<>();
  
  private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLockEx();
  //  private final List<IModelInterfacesDescription<T>> interfaceDescriptions = new ArrayList<>();
  private final Set<Class<? extends IModelInterface>> modelInterfaces = new HashSet<>();
  private final Set<Class<? extends IModelInvocationHandler>> modelHandlers = new HashSet<>();
  private volatile List<IModelInvocationHandler> invocationHandlers = new ArrayList<>();
  
  @Override
  public void addHandler(Class<? extends IModelInvocationHandler> handlerClass,
                         Class<? extends IModelProxy> proxyClass) {
    if (modelHandlers.contains(handlerClass)) {
      return;
    }
    modelHandlers.add(handlerClass);
    try {
      Constructor<? extends IModelInvocationHandler> declaredConstructor =
          handlerClass.getDeclaredConstructor(proxyClass);
      IModelInvocationHandler iModelInvocationHandler = declaredConstructor.newInstance(this);
      invocationHandlers.add(iModelInvocationHandler);
    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public Set<Class<? extends IModelInvocationHandler>> getHandlers() {
    checkReadLock();
    return Collections.unmodifiableSet(modelHandlers);
  }
  
  @Override
  public Set<Class<? extends IModelInterface>> getInterfaces() {
    checkWriteLock();
    return modelInterfaces;
  }
  
  @Override
  public Map<Object, Object> getData(Class<? extends IModelProxy> proxyClass) {
    return (Map<Object, Object>) data.computeIfAbsent(proxyClass, o -> new ConcurrentHashMap<>());
  }
  
  
  @Override
  public void configure(IModelProxyConfigurer configurer) {
    configurer.configure(this);
    
  }
  
  @Override
  public <I extends IModelInterface> I as(Class<I> cls) {
    if (!modelInterfaces.contains(cls)) {
      throw new IModelInterfaceNotAvailableException("Interface not available: " + cls);
    }
    
    I i = (I) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, this);
    return i;
  }
  
  @Override
  public IModelProxyInternal internal() {
    return this;
  }
  
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    // todo: fix thread safety
    for (IModelInvocationHandler handler : invocationHandlers) {
      IModelInvocationHandler.InvocationResult invocationResult = handler.doInvoke(proxy, method, args);
      if (invocationResult.isHandled()) {
        return invocationResult.getResult();
      }
    }
    
    throw new IllegalArgumentException("Could not handle invocation on object:  " + proxy.getClass() + " and method: "
        + method + " with args: " + (args == null ? null : Arrays.asList(args)));
  }
  
  @Override
  public Lock readLock() {
    return readWriteLock.readLock();
  }
  
  @Override
  public Lock writeLock() {
    return readWriteLock.writeLock();
  }
  
  private void checkReadLock() {
    if (!readWriteLock.isWriteLockedByCurrentThread()) {
      throw new IllegalStateException("Thread reading IModelProxy does not hold a read lock. Thread: " + Thread.currentThread());
    }
  }
  
  private void checkWriteLock() {
    if (!readWriteLock.isWriteLockedByCurrentThread()) {
      throw new IllegalStateException("Thread writing IModelProxy does not hold a write lock. Thread: " + Thread.currentThread());
    }
  }
  
}
