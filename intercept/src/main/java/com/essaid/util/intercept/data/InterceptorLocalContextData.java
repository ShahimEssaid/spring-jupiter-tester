package com.essaid.util.intercept.data;

public class InterceptorLocalContextData extends Data implements IInterceptorContextLocalData {
  
  @Override
  public <T extends IInterceptorContextLocalData> T as(Class<T> globalContextDataInterface) {
    return (T) super.doAs(globalContextDataInterface);
  }
}
