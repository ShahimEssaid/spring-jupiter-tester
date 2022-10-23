package com.essaid.util.intercept.data;

public interface IInterceptorGroupData {
  <T extends IInterceptorGroupData> T as(Class<T> interceptorGroupDataInterface);
}
