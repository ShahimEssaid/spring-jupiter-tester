package com.essaid.picocli.commands.unit;

import static org.assertj.core.api.Assertions.*;

import com.essaid.picocli.commands.ICommandType2;
import com.essaid.picocli.commands.ICommands2;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ICommandsTests {
  
  @Test
  void namedInstances(){
    ICommands2 defaultInstance = ICommands2.getDefaultInstance();
    assertThat(defaultInstance).isNotNull();
    assertThat(ICommands2.getInstance(ICommands2.DEFAULT_ICOMMANDS_INSTANCE_NAME)).isSameAs(defaultInstance);
  
    ICommands2 iCommands2 = ICommands2.removeInstance(ICommands2.DEFAULT_ICOMMANDS_INSTANCE_NAME);
    assertThat(iCommands2).isNotNull();
    assertThat(ICommands2.getInstance(ICommands2.DEFAULT_ICOMMANDS_INSTANCE_NAME)).isNull();;
  }
  
  @Test
  void defaultRoot(){
    ICommands2 defaultInstance = ICommands2.getDefaultInstance();
    Map<String, List<ICommandType2>> commandTypesByPath = defaultInstance.internal().getCommandTypesByPath();
    assertThat (commandTypesByPath.containsKey(ICommands2.DEFAULT_ROOT_COMMAND_PATH)).isTrue();
    
  }
  
}
