package tmp.springboot.example1.comp;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class SingletonBean {
  
  @PreDestroy
  void preDestroy() {
    System.out.println("============ Pre destroying SingletonBean");
  }
}
