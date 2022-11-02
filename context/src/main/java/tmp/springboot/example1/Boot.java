package tmp.springboot.example1;

import com.essaid.context.spring.ISpringContext;
import com.essaid.context.spring.ISpringScope;
import com.essaid.context.spring.SpringContextDomain;
import com.essaid.context.spring.SpringScopes;
import com.essaid.context.spring.SpringThreadManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.Scope;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import tmp.springboot.example1.comp.ApplicationBean;
import tmp.springboot.example1.comp.RequestBeanA;
import tmp.springboot.example1.comp.SessionBeanA;

@SpringBootApplication
public class Boot {
  
  public static void main(String[] args) {
    SpringContextDomain domain = new SpringContextDomain();
    SpringThreadManager.setDomain(domain);
    SpringApplication application = new SpringApplication(Boot.class) {
    };
    
    application.addInitializers(new ApplicationContextInitializer<ConfigurableApplicationContext>() {
      @Override
      public void initialize(ConfigurableApplicationContext applicationContext) {
        ISpringScope session = domain.createScope("session", 100, applicationContext, Thread.currentThread());
        ISpringScope request = domain.createScope("request", 101, applicationContext, Thread.currentThread());
        applicationContext.getBeanFactory().registerScope("session", session);
        applicationContext.getBeanFactory().registerScope("request", request);
        applicationContext.getBeanFactory().registerScope(SpringScopes.APPLICATION_NAME, domain);
        applicationContext.addApplicationListener(domain);
        
      }
    });
    
    ISpringContext context1 = SpringThreadManager.getContext();
    
    ConfigurableApplicationContext context = application.run(args);
    Scope session = context.getBeanFactory().getRegisteredScope("session");
    
    BeanDefinition sessionBeanADefinition = context.getBeanFactory().getBeanDefinition("sessionBeanA");
    String scope = sessionBeanADefinition.getScope();
    
    //String[] beanDefinitionNames = context.getBeanDefinitionNames();
    
    SessionBeanA sessionBeanA = context.getBean(SessionBeanA.class);
    RequestBeanA requestBeanA = context.getBean(RequestBeanA.class);
    ApplicationBean applicationBean = context.getBean(ApplicationBean.class);
    
    context.close();
    domain.close();
    System.out.println("Done");
    //Arrays.stream(beanDefinitionNames).sorted().forEach(System.out::println);
  }
}
