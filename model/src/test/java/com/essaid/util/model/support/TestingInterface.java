package com.essaid.util.model.support;

import com.essaid.util.model.IModelInterface;

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
