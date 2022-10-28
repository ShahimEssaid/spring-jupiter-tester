package com.essaid.util.modelproxy;

import java.util.Collection;

public interface IModelProxyConfigurer {
  
  String id();
  
  String name();
  
  String version();
  
  int priority();
  
  String shortDescription();
  
  String description();
  
  String notes();
  
  void configure(IModelProxy proxy);

}
