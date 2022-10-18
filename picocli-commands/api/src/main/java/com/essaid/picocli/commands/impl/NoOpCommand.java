package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.Command;
import com.essaid.picocli.commands.CommandInfo;
import com.essaid.picocli.commands.Constants;
import picocli.CommandLine;

@CommandInfo(groupId = Constants.GROUP_ID, artifactId = Constants.ARTIFACT_ID, artifactVersion =
    Constants.ARTIFACT_VERSION, commandId = "no" + "-op", commandVersion = "0.0.1")
@CommandLine.Command(name = "no-op", description = "A placeholder command", subcommandsRepeatable = true)
public class NoOpCommand extends Command {
  
  @Override
  public Integer call() throws Exception {
    return 0;
  }
}
