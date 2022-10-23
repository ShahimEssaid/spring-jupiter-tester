package com.essaid.util.intercept.data;

public interface IInterceptorContextGlobalData {
  <T extends IInterceptorContextGlobalData> T as(Class<T> globalContextDataInterface);
}
