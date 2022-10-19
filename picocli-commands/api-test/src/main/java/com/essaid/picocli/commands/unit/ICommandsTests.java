package com.essaid.picocli.commands.unit;

import static org.assertj.core.api.Assertions.*;

import com.essaid.picocli.commands.ICommands2;
import org.junit.jupiter.api.Test;

public class ICommandsTests {
  
  @Test
  void names(){
    ICommands2 defaultInstance = ICommands2.getDefaultInstance();
    assertThat(defaultInstance).isNotNull();
    assertThat(ICommands2.getInstance(ICommands2.DEFAULT_ICOMMANDS_INSTANCE_NAME)).isSameAs(defaultInstance);
  
    ICommands2 iCommands2 = ICommands2.removeInstance(ICommands2.DEFAULT_ICOMMANDS_INSTANCE_NAME);
    assertThat(iCommands2).isNotNull();
    assertThat(ICommands2.getInstance(ICommands2.DEFAULT_ICOMMANDS_INSTANCE_NAME)).isNull();;
  }
  
  
}
