package tmp.springboot.example1.comp;

import com.essaid.context.spring.IFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Scope(IFactory.APPLICATION_NAME)
@Component
public class ApplicationBean {
  public ApplicationBean() {
    System.out.println("============= ApplicationBean constructed");
  }
  
  
  @PreDestroy
  void preDestroy() {
    System.out.println("=============== in pre destroy ApplicationBean");
  }
}
