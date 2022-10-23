package com.essaid.util.intercept.data;

public interface IInterceptorContextLocalData {
  <T extends IInterceptorContextLocalData> T as(Class<T> localContextDataInterface);
}
