package com.essaid.pcli.test.unit;

import com.essaid.pcli.type.HelloWorldCommandRegistrant;
import com.essaid.pcli.type.ICommandType;
import com.essaid.pcli.type.ICommandTypeRegistrant;
import com.essaid.pcli.type.ICommandTypeRegistry;
import com.essaid.pcli.type.ICommandTypeUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ICommandTypeRegistryTests {
  
  @Test
  void registerHelloWorld() {
    ICommandTypeRegistry defaultRegistry = ICommandTypeRegistry.getDefaultRegistry();
    
    Map<ICommandTypeRegistrant, List<ICommandType>> iCommandTypeRegistrantListMap =
        ICommandTypeUtils.registerFromClassLoader(defaultRegistry, HelloWorldCommandRegistrant.class.getClassLoader());
  
    Assertions.assertThat(iCommandTypeRegistrantListMap).hasSize(1);
  }
  
}
