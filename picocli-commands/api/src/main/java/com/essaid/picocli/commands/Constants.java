package com.essaid.picocli.commands;

public interface Constants {
  String ARTIFACT_ID =  "com.essaid.picocli.commands";
  String GROUP_ID = "api";
  String ARTIFACT_VERSION = "0.0.0";
  
  String NOT_COMMAND_PREFIX = "_";
  
  String RESERVED_TEMPLATE_COMMAND_PREFIX = NOT_COMMAND_PREFIX + "pcc-template-command";
  
  String RESERVED_NO_OP_COMMAND_PATH = RESERVED_TEMPLATE_COMMAND_PREFIX + ".no-op";
  String RESERVED_ROOT_COMMAND_PATH = RESERVED_TEMPLATE_COMMAND_PREFIX + ".root";
  
}
