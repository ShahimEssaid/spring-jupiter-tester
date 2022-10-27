package com.essaid.util.modelproxy;

import com.essaid.util.modelproxy.bean.BeanPropertyProxyConfigurer;
import com.essaid.util.modelproxy.support.TestPropertyProxy;
import com.essaid.util.modelproxy.support.TestingInterface;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class ModelProxyTests {

  @Test
  void testOne(){
    TestPropertyProxy testProxy = new TestPropertyProxy();
    BeanPropertyProxyConfigurer<TestPropertyProxy> beanPropertyProxyConfigurer = new BeanPropertyProxyConfigurer<TestPropertyProxy>();
    testProxy.configure(beanPropertyProxyConfigurer);
    
    TestingInterface as = testProxy.as(TestingInterface.class);
    Assertions.assertThat(as).isNotNull();
    
    // String value
    Assertions.assertThat(as.getName()).isNull();
    as.setName("Shahim");
    Assertions.assertThat(as.getName()).isEqualTo("Shahim");
    as.setName(null);
    Assertions.assertThat(as.getName()).isNull();
    
    // boolean
    Assertions.assertThat(as.isCurrent()).isFalse();
    as.setCurrent(true);
    Assertions.assertThat(as.isCurrent()).isTrue();
  
    // int
    Assertions.assertThat(as.getNumber()).isEqualTo(0);
    as.setNumber(10);
    Assertions.assertThat(as.getNumber()).isEqualTo(10);
  }

}
