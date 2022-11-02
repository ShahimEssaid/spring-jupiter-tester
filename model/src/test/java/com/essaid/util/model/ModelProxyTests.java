package com.essaid.util.model;

import com.essaid.util.model.beanhandlers.BeanPropertyConfigurer;
import com.essaid.util.model.support.TestPropertyModel;
import com.essaid.util.model.support.TestingInterface;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

public class ModelProxyTests {
  
  @Test
  void testOne() {
    TestPropertyModel testProxy = new TestPropertyModel();
    BeanPropertyConfigurer beanPropertyProxyConfigurer =
        new BeanPropertyConfigurer();
    testProxy.appendConfiguration(beanPropertyProxyConfigurer);
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
    
    // Boolean
    Assertions.assertThat(as.getBoolean()).isNull();;
    as.setBoolean(true);
    Assertions.assertThat(as.getBoolean()).isEqualTo(Boolean.TRUE);
    
  }
  
}
