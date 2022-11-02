package tmp.springboot.example1.comp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")

public class RequestBeanA {
}
