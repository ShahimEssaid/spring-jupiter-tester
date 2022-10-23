package com.essaid.util.intercept;

import com.essaid.util.intercept.data.IDomainData;

public interface SomeDomainData extends IDomainData {
  
  boolean hasName();
  
  void createName(String name);
  
  String readName();
  
  String updateName(String name);
  
  String deleteName();
  
  void clearData();
  
  void badMethod();
  
  String getName();
  
  String setName(String name);
  
}


