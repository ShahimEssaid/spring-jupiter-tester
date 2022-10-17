package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.Command;
import com.essaid.picocli.commands.CommandInfo;
import picocli.CommandLine;

@CommandInfo(groupId = "com.essaid.picocli.commands", artifactId = "api", commandId = "no-op", version = "0.0.1")
@CommandLine.Command(name = "no-op", description = "A placeholder command", subcommandsRepeatable = true)
public class NoOpCommand extends Command {
  
  @Override
  public Integer call() throws Exception {
    return 0;
  }
}
