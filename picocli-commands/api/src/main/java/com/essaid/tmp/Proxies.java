package com.essaid.tmp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Proxies {
  
  public static void main(String[] args) {
  
  
  }
  
  public static A getAProxy() {
    
    InvocationHandler ih = new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isDefault()) {
          return InvocationHandler.invokeDefault(proxy, method, args);
        } else {
          throw new UnsupportedOperationException("Not implemented");
        }
      }
    };
    
    Object o = Proxy.newProxyInstance(A.class.getClassLoader(), new Class<?>[]{A.class}, ih);
    
    return (A) o;
  }
}
