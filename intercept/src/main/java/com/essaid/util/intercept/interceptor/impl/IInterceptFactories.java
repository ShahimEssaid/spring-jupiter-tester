package com.essaid.util.intercept.interceptor.impl;

import com.essaid.util.intercept.context.IContextFactory;
import com.essaid.util.intercept.data.IDataFactory;
import com.essaid.util.model.IModelInterface;

public interface IInterceptFactories extends IModelInterface {
  
  IContextFactory getIContextFactory();
  
  void setIContextFactory(IContextFactory contextFactory);
  
  IDataFactory getIDataFactory();
  
  void setIDataFactory(IDataFactory iDataFactory);
  
}
