package com.essaid.util.model.impl;

import com.essaid.util.model.IModel;
import com.essaid.util.model.IModelConfigurer;
import com.essaid.util.model.IModelInterface;
import com.essaid.util.model.IModelInterfaceNotAvailableException;
import com.essaid.util.model.IModelInvocationHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Model implements IModel.IModelInternal, InvocationHandler {
  
  private final boolean permissive;
  private final Map<Object, Object> data = new ConcurrentHashMap<>();
  private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  //  private final List<IModelInterfacesDescription<T>> interfaceDescriptions = new ArrayList<>();
  private final List<Class<? extends IModelInterface>> modelInterfaces = new ArrayList<>();
  private final List<Class<? extends IModelInvocationHandler>> modelHandlers = new ArrayList<>();
  private volatile List<IModelInvocationHandler> invocationHandlers = new ArrayList<>();
  
  public Model(boolean permissive) {
    this.permissive = permissive;
  }
  
  public Model() {
    this.permissive = false;
  }
  
  @Override
  public void addHandler(Class<? extends IModelInvocationHandler> handlerClass, Class<? extends IModel> modelClass,
                         boolean append) {
    if (modelHandlers.contains(handlerClass)) {
      return;
    }
    
    if (append) {
      modelHandlers.add(handlerClass);
    } else {
      modelHandlers.add(0, handlerClass);
    }
    
    try {
      Constructor<? extends IModelInvocationHandler> declaredConstructor =
          handlerClass.getDeclaredConstructor(modelClass);
      IModelInvocationHandler iModelInvocationHandler = declaredConstructor.newInstance(this);
      if (append) {
        invocationHandlers.add(iModelInvocationHandler);
      } else {
        invocationHandlers.add(0, iModelInvocationHandler);
      }
    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public List<Class<? extends IModelInvocationHandler>> getHandlers() {
    checkReadLock();
    return Collections.unmodifiableList(modelHandlers);
  }
  
  @Override
  public List<Class<? extends IModelInterface>> getInterfaces() {
    checkWriteLock();
    return modelInterfaces;
  }
  
  @Override
  public Map<Object, Object> getData(Class<? extends IModel> proxyClass) {
    return (Map<Object, Object>) data.computeIfAbsent(proxyClass, o -> new ConcurrentHashMap<>());
  }
  
  
  @Override
  public void appendConfiguration(List<IModelConfigurer> configurers) {
    if(configurers == null) return;
    configurers.forEach(iModelConfigurer -> iModelConfigurer.configure(this, true));
  }
  
  @Override
  public void prependConfiguration(List<IModelConfigurer> configurers) {
    if(configurers == null) return;
    configurers.forEach(iModelConfigurer -> iModelConfigurer.configure(this, false));
  }
  
  @Override
  public <I extends IModelInterface> I as(Class<I> cls) {
    if (permissive) {
      modelInterfaces.add(cls);
    } else {
      if (!modelInterfaces.contains(cls)) {
        throw new IModelInterfaceNotAvailableException("Interface not available: " + cls);
      }
    }
    
    I i = (I) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, this);
    return i;
  }
  
  @Override
  public IModelInternal internal() {
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
