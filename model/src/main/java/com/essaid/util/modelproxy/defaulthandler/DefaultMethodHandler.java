package com.essaid.util.modelproxy.defaulthandler;

import com.essaid.util.modelproxy.IModelInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultMethodHandler implements IModelInvocationHandler {
  @Override
  public InvocationResult doInvoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (method.isDefault()) {
      return new InvocationResult(true, InvocationHandler.invokeDefault(proxy, method, args));
    } else {
      return IModelInvocationHandler.UN_HANDLED;
    }
  }
}
