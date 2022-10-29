package com.essaid.util.model;

public interface IModelConfigurer {
  
  String id();
  
  String name();
  
  String version();
  
  int priority();
  
  String shortDescription();
  
  String description();
  
  String notes();
  
  void configure(IModel proxy, boolean append);

}
