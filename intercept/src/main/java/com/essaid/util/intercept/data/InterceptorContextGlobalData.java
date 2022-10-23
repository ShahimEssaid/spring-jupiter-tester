package com.essaid.util.intercept.data;

public class InterceptorContextGlobalData extends Data implements IInterceptorContextGlobalData {
  
  @Override
  public <T extends IInterceptorContextGlobalData> T as(Class<T> globalContextDataInterface) {
    return (T) super.doAs(globalContextDataInterface);
  }
}
