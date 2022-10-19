package com.essaid.picocli.commands.tmp;

import jdk.jfr.Name;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class AnnotatedGenericBeanDefinitionExample {
  
  public static void main(String[] args) {
    AnnotatedGenericBeanDefinition bd = new AnnotatedGenericBeanDefinition(B.class);
    System.out.println("getBeanClassName:  " + bd.getBeanClassName());
    System.out.println("getFactoryBeanName:  " + bd.getFactoryBeanName());
    System.out.println("getFactoryMethodName:  " + bd.getFactoryMethodName());
    System.out.println("getParentName:  " + bd.getParentName());
    System.out.println(bd);
  
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
    ac.register(B.class);
    Arrays.stream(ac.getDefaultListableBeanFactory().getBeanDefinitionNames()).sorted().forEach(s -> {
      BeanDefinition beanDefinition = ac.getBeanDefinition(s);
      System.out.println("Name: " + s + " definition: " + ac.getBeanDefinition(s));
    });
    
  }
}

@Qualifier("b-qualifier")
@Component("b-name")
class B {

}
