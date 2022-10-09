package com.essaid.spring.jupiter.tester;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

public class Util {
  
  public static String printContext(ConfigurableApplicationContext applicationContext) {
    StringBuilder sb = new StringBuilder();
    
    sb.append("\n======= Parents  =========\n");
    sb.append("Context parent: " + applicationContext.getParent() + "\n");
    sb.append("Parent bean factory: " + applicationContext.getParentBeanFactory() + "\n");
    
    sb.append("\n======= Bean definitions =======\n");
    sb.append(printBeanDefinitions(applicationContext));
    
    ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
    sb.append("\n======= Beans =======\n");
    sb.append(printBeans(applicationContext));
    return sb.toString();
  }
  
  public static String printBeanDefinitions(ConfigurableApplicationContext applicationContext) {
    StringBuilder sb = new StringBuilder();
    
    Arrays.stream(applicationContext.getBeanFactory().getBeanDefinitionNames()).sorted().forEach(name -> {
      BeanDefinition bd = applicationContext.getBeanFactory().getBeanDefinition(name);
      sb.append(name + ": " + bd + "\n");
    });
    
    return sb.toString();
  }
  
  public static String printBeans(ConfigurableApplicationContext applicationContext) {
    StringBuilder sb = new StringBuilder();
    if (applicationContext.isActive()) {
      Arrays.stream(applicationContext.getBeanFactory().getBeanDefinitionNames()).sorted().forEach(name -> {
        Object bean = applicationContext.getBean(name);
        sb.append(name + ": " + bean + "\n");
      });
    } else {
      sb.append("Not active!\n");
    }
    
    return sb.toString();
  }
  
  
}
