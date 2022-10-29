package com.essaid.util.intercept.config;

import com.essaid.util.model.IModelInterface;
import com.essaid.util.model.IModelConfigurer;

import java.util.List;

public interface IDomainInterceptConfigurationInterface extends IModelInterface {
  
  boolean isPermissive();
  
  void setPermissive(boolean isPermissive);
  
  List<IModelConfigurer> getDataConfigurers();
  
  void getDataConfigurers(List<IModelConfigurer> configurers);
  
  List<IModelConfigurer> getDataLocalConfigurers();
  
  void getDataLocalConfigurers(List<IModelConfigurer> configurers);
}
