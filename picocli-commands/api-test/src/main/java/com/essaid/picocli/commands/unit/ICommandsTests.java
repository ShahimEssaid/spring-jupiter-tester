package com.essaid.picocli.commands.unit;

import static org.assertj.core.api.Assertions.*;

import com.essaid.picocli.commands.ICommandType;
import com.essaid.picocli.commands.ICommands;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ICommandsTests {
  
  @Test
  void namedInstances(){
    ICommands defaultInstance = ICommands.getDefaultInstance();
    assertThat(defaultInstance).isNotNull();
    assertThat(ICommands.getInstance(ICommands.DEFAULT_ICOMMANDS_INSTANCE_NAME)).isSameAs(defaultInstance);
  
    ICommands iCommands = ICommands.removeInstance(ICommands.DEFAULT_ICOMMANDS_INSTANCE_NAME);
    assertThat(iCommands).isNotNull();
    assertThat(ICommands.getInstance(ICommands.DEFAULT_ICOMMANDS_INSTANCE_NAME)).isNull();;
  }
  
  @Test
  void defaultRoot(){
    ICommands defaultInstance = ICommands.getDefaultInstance();
    Map<String, List<ICommandType>> commandTypesByPath = defaultInstance.internal().getCommandTypesByPath();
    assertThat (commandTypesByPath.containsKey(ICommands.DEFAULT_ROOT_COMMAND_PATH)).isTrue();
    
  }
  
}
