package com.essaid.picocli.commands.unit;

import static org.assertj.core.api.Assertions.*;

import com.essaid.picocli.commands.type.ICommandType;
import com.essaid.picocli.commands.ICommands;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ICommandsTests {
  
  @Test
  void namedInstances(){
    ICommands defaultInstance = ICommands.getDefaultCommandsInstance();
    assertThat(defaultInstance).isNotNull();
    assertThat(ICommands.getCommandsInstance(ICommands.DEFAULT_ICOMMANDS_NAME)).isSameAs(defaultInstance);
  
    ICommands iCommands = ICommands.removeCommandsInstance(ICommands.DEFAULT_ICOMMANDS_NAME);
    assertThat(iCommands).isNotNull();
    assertThat(ICommands.getCommandsInstance(ICommands.DEFAULT_ICOMMANDS_NAME)).isNull();;
  }
  
  @Test
  void defaultRoot(){
    ICommands defaultInstance = ICommands.getDefaultCommandsInstance();
    Map<String, List<ICommandType>> commandTypesByPath = defaultInstance.internal().getCommandTypesByPath();

    
  }
  
}
