package tmp.springboot.example1.comp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class SingletonBeanA {
  
  @Autowired
  public ApplicationBeanA applicationBeanA;
  
  @Autowired
  public SingletonBeanA singletonBeanA;
  
  @Autowired
  public SessionBeanA sessionBeanA;
  
  @Autowired
  public RequestBeanA requestBeanA;
  
  public String value = "SingletonBeanA";
  
  
  @PreDestroy
  void preDestroy() {
    System.out.println("============ Pre destroying SingletonBean");
  }
}
