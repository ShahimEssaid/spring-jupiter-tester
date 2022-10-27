package com.essaid.util.modelproxy;

import java.util.Collection;

public interface IModelProxyConfigurer<P extends IModelProxy<P>> {
  
  String id();
  
  String name();
  
  String version();
  
  int priority();
  
  String shortDescription();
  
  String description();
  
  String notes();
  
  void configure(IModelProxy<P> proxy);

}
