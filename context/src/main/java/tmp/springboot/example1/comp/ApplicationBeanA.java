package tmp.springboot.example1.comp;

import com.essaid.context.spring.IFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Scope(IFactory.APPLICATION_NAME)
@Component
public class ApplicationBeanA {
  public ApplicationBeanA() {
    System.out.println("============= ApplicationBean constructed");
  }
  
  
  @Autowired
  public ApplicationBeanA applicationBeanA;
  
  @Autowired
  public SingletonBeanA singletonBeanA;
  
  @Autowired
  public SessionBeanA sessionBeanA;
  
  @Autowired
  public RequestBeanA requestBeanA;
  
  String value = "ApplicationBeanA";
  
  @PreDestroy
  void preDestroy() {
    System.out.println("=============== in pre destroy ApplicationBean");
  }
}
