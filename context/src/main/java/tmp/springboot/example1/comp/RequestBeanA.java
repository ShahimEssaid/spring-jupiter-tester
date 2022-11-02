package tmp.springboot.example1.comp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Scope("request")

public class RequestBeanA {
  
  @PreDestroy
  void preDestroy(){
    System.out.println("=============== in pre destroy RequestBeanA");
  }
}
