package com.essaid.pcli.factory;

import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFactory implements IFactoryExtended {
  
  private final CommandLine.IFactory delegate;
  private List<Exception> exceptions;
  
  public AbstractFactory() {
    this.delegate = null;
  }
  
  public AbstractFactory(CommandLine.IFactory delegateFactory) {
    this.delegate = delegateFactory;
  }
  
  @Override
  final public <K> K create(Class<K> cls) throws Exception {
    exceptions = new ArrayList<>();
    return delegateCreate(cls, exceptions);
  }
  
  @Override
  final public <K> K delegateCreate(Class<K> cls, List<Exception> exceptions) throws Exception {
    if (Objects.isNull(cls)) {
      throw new NullPointerException("Class passed to IFactory can't be null. Factory: " + this);
    }
    
    K k = null;
    try {
      k = doCreate(cls);
    } catch (Exception ex) {
      exceptions.add(0, ex);
    }
    
    if (k != null) {
      return k;
    }
    
    if (delegate != null) {
      try {
        if (delegate instanceof IFactoryExtended delegateEx) {
          k = delegateEx.delegateCreate(cls, exceptions);
        } else {
          k = delegate.create(cls);
        }
      } catch (Exception ex) {
        exceptions.add(0, ex);
      }
    }
    
    if (k == null && !exceptions.isEmpty()) {
      throw exceptions.get(0);
    }
    
    return k;
  }
  
  @Override
  public CommandLine.IFactory getDelegate() {
    return delegate;
  }
  
  @Override
  public List<Exception> getExceptions() {
    return exceptions;
  }
  
  @Override
  public void reset() {
    this.exceptions = null;
  }
  
  @Override
  public <K> K postProcess(K k) {
    return k;
  }
}
