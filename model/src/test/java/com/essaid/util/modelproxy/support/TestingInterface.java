package com.essaid.util.modelproxy.support;

import com.essaid.util.modelproxy.IModelInterface;

public interface TestingInterface extends IModelInterface {
  
  String getName();
  
  void setName(String name);
  
  int getNumber();
  
  void setNumber(int number);
  
  boolean isCurrent();
  
  void setCurrent(boolean isCurrent);
  
  Boolean getBoolean();
  
  void setBoolean(Boolean isCurrent);
}
