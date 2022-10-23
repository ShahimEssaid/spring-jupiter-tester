package com.essaid.util.intercept.data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Data implements InvocationHandler {
  
  static Pattern NORMALIZED_PATTERN = Pattern.compile("([A-Z])");
  private final Map<Class<?>, Map<String, Object>> data = new ConcurrentHashMap<>();
  
  static private void checkClass(Class<?> domainDataInterface) {
    if (!domainDataInterface.isInterface()) {
      throw new IllegalArgumentException("Class must be an interface: " + domainDataInterface);
    }
  }
  
  static private String getNormalizedName(String methodName) {
    Matcher matcher = NORMALIZED_PATTERN.matcher(methodName);
    StringBuilder sb = new StringBuilder();
    while (matcher.find()) {
      matcher.appendReplacement(sb, "." + matcher.group(1).toLowerCase());
    }
    matcher.appendTail(sb);
    return sb.toString();
  }
  
  public static void main(String[] args) {
    String normalizedName = getNormalizedName("OneTwoTThree");
    System.out.println(getNormalizedName(normalizedName));
    System.out.println(normalizedName.substring(normalizedName.indexOf(".") + 1, normalizedName.length()));
  }
  
  protected Object doAs(Class<?> domainDataInterface) {
    checkClass(domainDataInterface);
    return Proxy.newProxyInstance(domainDataInterface.getClassLoader(), new Class[]{domainDataInterface}, this);
  }
  
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    
    if (method.isDefault()) {
      return InvocationHandler.invokeDefault(proxy, method, args);
    }
    
    String normalizedMethodName = getNormalizedName(method.getName());
    String propertyName = normalizedMethodName.substring(normalizedMethodName.indexOf(".") + 1,
        normalizedMethodName.length());
    
    Class<?> declaringInterface = method.getDeclaringClass();
    Map<String, Object> interfaceData = data.get(declaringInterface);
    
    if (normalizedMethodName.startsWith("get.")) {
      return interfaceData == null ? null : interfaceData.get(propertyName);
    }
    
    if (normalizedMethodName.startsWith("set.")) {
      Object value = args[0];
      
      if (value == null) {
        return interfaceData == null ? null : interfaceData.remove(propertyName);
      }
      
      if (interfaceData == null) {
        interfaceData = new ConcurrentHashMap<>();
        data.put(declaringInterface, interfaceData);
      }
      
      return interfaceData.put(propertyName, value);
    }
    
    if (normalizedMethodName.startsWith("has.")) {
      return interfaceData == null ? false : interfaceData.containsKey(propertyName);
    }
    
    if (normalizedMethodName.startsWith("create.")) {
      Object value = args[0];
      
      if (value == null) {
        throw new NullValueException("Property " + declaringInterface.getName() + ":" + propertyName + " being " + "created but " +
            "the value is null.");
      }
      
      if (interfaceData != null && interfaceData.containsKey(propertyName)) {
        throw new ExistingValueException("Property " + declaringInterface.getName() + ":" + propertyName + " already " + "existed with " +
            "value:" + interfaceData.get(propertyName) + " while assigning value: " + args[0]);
      }
      
      if (interfaceData == null) {
        interfaceData = new ConcurrentHashMap<>();
        data.put(declaringInterface, interfaceData);
      }
      
      interfaceData.put(propertyName, args[0]);
      return null;
    }
    
    if (normalizedMethodName.startsWith("read.")) {
      return interfaceData == null ? null : interfaceData.get(propertyName);
    }
    
    if (normalizedMethodName.startsWith("update.")) {
      Object value = args[0];
      
      if (value == null) {
        throw new NullValueException("Property " + declaringInterface.getName() + ":" + propertyName + " being " +
            "updated but the new value is null.");
      }
      
      if (interfaceData == null || !interfaceData.containsKey(propertyName)) {
        throw new NonExistingValueException("Property " + declaringInterface.getName() + ":" + propertyName + " did not " + "exist" + " " +
            "while updating with value: " + value);
      }
      
      return interfaceData.put(propertyName, value);
    }
    
    if (normalizedMethodName.startsWith("delete.")) {
      if (interfaceData == null || !interfaceData.containsKey(propertyName)) {
        throw new NonExistingValueException("Property " + declaringInterface.getName() + ":" + propertyName + " did not " + "exist while " +
            "deleting.");
      }
      return interfaceData.remove(propertyName);
    }
    
    if (normalizedMethodName.equals("clear.data")) {
      data.remove(declaringInterface);
      return null;
    }
    
    throw new BadDataMethod("Method is not according to the CRUD pattern: " + method);
    
  }
}
