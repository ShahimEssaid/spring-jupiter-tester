package com.essaid.tmp;

public interface B extends  ISuper{
  default String name() {
    return "B";
  }
  
  String address();
}
