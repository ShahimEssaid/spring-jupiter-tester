package com.essaid.util.modelproxy.bean;

import com.essaid.util.modelproxy.IModelInvocationHandler;
import com.essaid.util.modelproxy.ModelProxyUtil;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanPropertyHandler implements IModelInvocationHandler {
  
  private final IModelInvocationHandler.InvocationResult UN_HANDLED =
      new IModelInvocationHandler.InvocationResult(false, null);
  
  private final IModelInvocationHandler.InvocationResult HANDLED_NULL =
      new IModelInvocationHandler.InvocationResult(true, null);
  private final IModelInvocationHandler.InvocationResult HANDLED_true =
      new IModelInvocationHandler.InvocationResult(true, true);
  private final IModelInvocationHandler.InvocationResult HANDLED_false =
      new IModelInvocationHandler.InvocationResult(true, false);
  
  
  private final IBeanPropertyProxy beanProxy;
  
  public BeanPropertyHandler(IBeanPropertyProxy beanProxy) {
    this.beanProxy = beanProxy;
  }
  
  @Override
  public InvocationResult doInvoke(Object proxy, Method method, Object[] args) {
    String methodName = method.getName();
    String normalizedMethodName = ModelProxyUtil.getNormalizedName(methodName);
    
    if (!normalizedMethodName.startsWith("get.") && !normalizedMethodName.startsWith("is.") && !normalizedMethodName.startsWith("set" + ".")) {
      return UN_HANDLED;
    }
    
    String propertyName = normalizedMethodName.substring(normalizedMethodName.indexOf(".") + 1,
        normalizedMethodName.length());
    
    Object dataKey = getDataKey(method);
    
    Map<Object, Object> beansProxyDataSpace = beanProxy.getBeansData();
    Map<String, Object> interfacePropertyValues = (Map<String, Object>) beansProxyDataSpace.get(dataKey);
    
    if (normalizedMethodName.startsWith("get.")) {
      // check for actual values
      Object returnValue = interfacePropertyValues == null ? null : interfacePropertyValues.get(propertyName);
      
      // defaults for primitives
      if (returnValue == null) {
        if (method.getReturnType().isPrimitive()) {
          Class returnType = method.getReturnType();
          if (Byte.TYPE.equals(returnType)) returnValue = 0;
          if (Short.TYPE.equals(returnType)) returnValue = 0;
          if (Integer.TYPE.equals(returnType)) returnValue = 0;
          if (Long.TYPE.equals(returnType)) returnValue = 0L;
          if (Float.TYPE.equals(returnType)) returnValue = 0.0f;
          if (Double.TYPE.equals(returnType)) returnValue = 0.0d;
          if (Character.TYPE.equals(returnType)) returnValue = Character.MIN_VALUE;
          if (Boolean.TYPE.equals(returnType)) returnValue = false;
        }
      }
      // return what we have
      return new InvocationResult(true, returnValue);
    } else if (normalizedMethodName.startsWith("is.")) {
      if (Boolean.TRUE.equals(interfacePropertyValues.get(propertyName))) {
        return HANDLED_true;
      } else {
        return HANDLED_false;
      }
    } else {
      Object value = args[0];
      
      if (value == null) {
        if (interfacePropertyValues != null) {
          interfacePropertyValues.remove(propertyName);
        }
      } else {
        if (interfacePropertyValues == null) {
          interfacePropertyValues = new ConcurrentHashMap<>();
          beansProxyDataSpace.put(dataKey, interfacePropertyValues);
        }
        interfacePropertyValues.put(propertyName, value);
      }
      return HANDLED_NULL;
    }
  }
  
  private Object getDataKey(Method method) {
    return method.getDeclaringClass();
  }
}
