package com.essaid.util.intercept.data;

public class InterceptorGroupData extends Data implements  IInterceptorGroupData {
  
  @Override
  public <T extends IInterceptorGroupData> T as(Class<T> interceptorGroupDataInterface) {
    return (T) super.doAs(interceptorGroupDataInterface);
  }
}
