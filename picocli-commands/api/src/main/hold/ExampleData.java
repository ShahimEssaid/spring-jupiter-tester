package com.essaid.util.intercept.tmp;

public interface ExampleData extends IInterceptorData {
  
  Class<ExampleData> self = ExampleData.class;
  String NAME_KEY = "name";
  
  default String getName() {
    return getValue(self, NAME_KEY);
  }
  
  default void setName(String name) {
    setValue(self, NAME_KEY, name);
  }
}
