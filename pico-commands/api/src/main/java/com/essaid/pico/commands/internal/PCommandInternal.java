package com.essaid.pico.commands.internal;

import com.essaid.pico.commands.PCommand;
import com.essaid.pico.commands.PCommandType;

public interface PCommandInternal extends PCommand {
  void setPCommandType(PCommandType type);
}
