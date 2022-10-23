package com.essaid.tmp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class Super implements ISuper, InvocationHandler {
  
  public static void main(String[] args) {
    Super aSuper = new Super();
    B proxy = createProxy(aSuper, B.class);
    System.out.println(proxy.name());
    
    String address = proxy.address();
    
    System.out.println(A.class.isInterface());
    
  }
  
  static <T extends ISuper> T createProxy(InvocationHandler ih, Class<T> cls) {
    return (T) Proxy.newProxyInstance(A.class.getClassLoader(), new Class[]{B.class, A.class}, ih);
  }
  
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (method.isDefault()) {
      return InvocationHandler.invokeDefault(proxy, method, args);
    } else {
      throw new UnsupportedOperationException("Not implemented");
    }
  }
  
  void test(List<String> strings) {
  
  }
  
}
