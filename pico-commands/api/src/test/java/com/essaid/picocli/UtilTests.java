package com.essaid.picocli;

import com.essaid.picocli.test.CommandWithSubcommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilTests extends BaseTest {
  
  
  @Test
  void noSubCommands(){
    boolean noSubCommands = Util.isWithoutSubCommands(CommandWithSubcommand.class);
    Assertions.assertEquals(noSubCommands, false);
  }
}
