package com.essaid.util.modelproxy.bean;

import com.essaid.util.modelproxy.IModelInvocationHandler;
import com.essaid.util.modelproxy.ModelProxyUtil;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GetSetHandler<P extends IBeanProxy<P>> implements IModelInvocationHandler<P> {
  
  private final IModelInvocationHandler.InvocationResult UN_HANDLED =
      new IModelInvocationHandler.InvocationResult(false, null);
  private final IModelInvocationHandler.InvocationResult HANDLED =
      new IModelInvocationHandler.InvocationResult(true, null);
  private final IBeanProxy<P> beanProxy;
  
  public GetSetHandler(IBeanProxy<P> beanProxy) {
    this.beanProxy = beanProxy;
  }
  
  @Override
  public InvocationResult doInvoke(Object proxy, Method method, Object[] args) {
    String methodName = method.getName();
    String normalizedMethodName = ModelProxyUtil.getNormalizedName(methodName);
    
    if (!normalizedMethodName.startsWith("get.") && !normalizedMethodName.startsWith("set.")) {
      return UN_HANDLED;
    }
    
    String propertyName = normalizedMethodName.substring(normalizedMethodName.indexOf(".") + 1,
        normalizedMethodName.length());
    
    Object dataKey = getDataKey(method);
    Map<Object, Object> data = beanProxy.getGetSetData();
    Map<String, Object> properties = (Map<String, Object>) data.get(dataKey);
    
    if (normalizedMethodName.startsWith("get.")) {
      return new InvocationResult(true, properties == null ? null : properties.get(propertyName));
    } else {
      Object value = args[0];
      
      if (value == null) {
        if(properties!=null){
          properties.remove(propertyName);
        }
      } else {
        if(properties == null){
          properties = new ConcurrentHashMap<>();
          data.put(dataKey, properties);
        }
        properties.put(propertyName, value);
      }
      return HANDLED;
    }
  }
  
  private Object getDataKey(Method method) {
    return method.getDeclaringClass();
  }
}
