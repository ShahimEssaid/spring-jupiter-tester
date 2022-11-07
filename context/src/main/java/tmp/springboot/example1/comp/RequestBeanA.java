package tmp.springboot.example1.comp;

import com.essaid.context.spring.IContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Scope(scopeName = IContainer.REQUEST_NAME, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestBeanA extends StringHolder {
  
  
  @Autowired
  public ApplicationBeanA applicationBeanA;
  
  @Autowired
  public SingletonBeanA singletonBeanA;
  
  @Autowired
  public SessionBeanA sessionBeanA;
  
  @Autowired
  public RequestBeanA requestBeanA;
  
  
  @PreDestroy
  void preDestroy() {
    System.out.println("=============== in pre destroy RequestBeanA");
  }
}
