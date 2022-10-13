package com.essaid.picocli.commands.internal;

import com.essaid.picocli.commands.PCommand;
import com.essaid.picocli.commands.PCommandType;

public interface PCommandInternal extends PCommand {
  void setPCommandType(PCommandType type);
}
