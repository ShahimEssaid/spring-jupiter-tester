package tmp.springboot.example1.comp;

import com.essaid.context.spring.IContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Scope(IContainer.SESSION_NAME)
public class SessionBeanA {
  
  
  @Autowired
  public ApplicationBeanA applicationBeanA;
  
  @Autowired
  public SingletonBeanA singletonBeanA;
  
  @Autowired
  public SessionBeanA sessionBeanA;
  
  @Autowired
  public RequestBeanA requestBeanA;
  
  String value = "SessionBeanA";
  
  
  @PreDestroy
  void preDestroy() {
    System.out.println("=============== in pre destroy SessionBeanA");
  }
}
