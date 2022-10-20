package com.essaid.picocli.commandsold.impl;

import com.essaid.picocli.commandsold.Command;
import com.essaid.picocli.commandsold.CommandInfo;
import com.essaid.picocli.commandsold.Constants;
import com.essaid.picocli.commands.picocli.CommandLine;

@CommandInfo(groupId = Constants.GROUP_ID, artifactId = Constants.ARTIFACT_ID, artifactVersion =
    Constants.ARTIFACT_VERSION, commandId = "no" + "-op", commandVersion = "0.0.1")
@CommandLine.Command(name = "no-op", description = "A placeholder command", subcommandsRepeatable = true)
public class NoOpCommand extends Command {
  
  @Override
  public Integer call() throws Exception {
    return 0;
  }
}
