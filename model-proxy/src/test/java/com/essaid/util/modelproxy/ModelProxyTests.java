package com.essaid.util.modelproxy;

import com.essaid.util.modelproxy.bean.GetSetModelProxyConfigurer;
import com.essaid.util.modelproxy.support.TestProxy;
import com.essaid.util.modelproxy.support.TestingInterface;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

public class ModelProxyTests {

  @Test
  void testOne(){
    TestProxy testProxy = new TestProxy();
    GetSetModelProxyConfigurer<TestProxy> getSetModelProxyConfigurer = new GetSetModelProxyConfigurer<TestProxy>();
    testProxy.configure(getSetModelProxyConfigurer);
    
    TestingInterface as = testProxy.as(TestingInterface.class);
    Assertions.assertThat(as).isNotNull();
    as.setName("Shahim");
    Assertions.assertThat(as.getName()).isEqualTo("Shahim");
  }

}
