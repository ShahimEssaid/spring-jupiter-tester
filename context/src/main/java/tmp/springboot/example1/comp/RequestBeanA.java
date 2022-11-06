package tmp.springboot.example1.comp;

import com.essaid.context.spring.IFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Scope(IFactory.REQUEST_NAME)
public class RequestBeanA {
  
  
  @Autowired
  public ApplicationBeanA applicationBeanA;
  
  @Autowired
  public SingletonBeanA singletonBeanA;
  
  @Autowired
  public SessionBeanA sessionBeanA;
  
  @Autowired
  public RequestBeanA requestBeanA;
  
  String value = "RequestBeanA";
  
  
  @PreDestroy
  void preDestroy() {
    System.out.println("=============== in pre destroy RequestBeanA");
  }
}
