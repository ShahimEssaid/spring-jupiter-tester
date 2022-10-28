package com.essaid.util.modelproxy;

import com.essaid.util.modelproxy.bean.BeanPropertyProxyConfigurer;
import com.essaid.util.modelproxy.support.TestPropertyProxy;
import com.essaid.util.modelproxy.support.TestingInterface;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

public class ModelProxyTests {
  
  @Test
  void testOne() {
    TestPropertyProxy testProxy = new TestPropertyProxy();
    BeanPropertyProxyConfigurer beanPropertyProxyConfigurer =
        new BeanPropertyProxyConfigurer();
    testProxy.configure(beanPropertyProxyConfigurer);
    TestingInterface as = null;
    
    Assertions.assertThatExceptionOfType(IModelInterfaceNotAvailableException.class)
        .isThrownBy(() -> testProxy.as(TestingInterface.class));
  
    Lock lock = testProxy.writeLock();
    lock.lock();
    testProxy.getInterfaces().add(TestingInterface.class);
    lock.unlock();
    
    as = testProxy.as(TestingInterface.class);
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
