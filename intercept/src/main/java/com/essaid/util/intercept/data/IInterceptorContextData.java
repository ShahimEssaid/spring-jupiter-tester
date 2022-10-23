package com.essaid.util.intercept.data;

public interface IInterceptorContextData {
  <T extends IInterceptorGroupData> T as(Class<T> contextDataInterface);
}
