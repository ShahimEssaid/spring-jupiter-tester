package com.essaid.tmp;

public interface A extends ISuper {
  
  default String name(){
    return  "A";
  }
}
