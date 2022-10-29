package com.essaid.util.intercept.config;

import com.essaid.util.intercept.context.IContextFactory;
import com.essaid.util.intercept.data.IDataFactory;
import com.essaid.util.model.IModelConfigurer;
import com.essaid.util.model.IModelInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface IInterceptConfig extends IModelInterface {
  
  IContextFactory getIContextFactory();
  
  void setIContextFactory(IContextFactory contextFactory);
  
  IDataFactory getIDataFactory();
  
  void setIDataFactory(IDataFactory iDataFactory);
  
  boolean isContextDataPermissive();
  
  void setContextDataPermissive(boolean isPermissive);
  
  boolean isContextLocalDataPermissive();
  
  void setContextLocalDataPermissive(boolean isPermissive);
  
  List<IModelConfigurer> getContextDataConfigurers();
  
  default void setContextDataConfigurers(IModelConfigurer... configurers) {
    setContextDataConfigurers(new ArrayList<>(Arrays.asList(configurers)));
  }
  
  void setContextDataConfigurers(List<IModelConfigurer> configurers);
  
  List<IModelConfigurer> getContextLocalDataConfigurers();
  
  default void setContextLocalDataConfigurers(IModelConfigurer... configurers) {
    setContextLocalDataConfigurers(new ArrayList<>(Arrays.asList(configurers)));
  }
  
  void setContextLocalDataConfigurers(List<IModelConfigurer> configurers);
  
  
}
