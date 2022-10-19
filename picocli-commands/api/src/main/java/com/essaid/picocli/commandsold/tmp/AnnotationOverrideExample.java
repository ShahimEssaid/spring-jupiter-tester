package com.essaid.picocli.commandsold.tmp;

import com.essaid.picocli.commandsold.CommandInfo;

@CommandInfo(groupId = "group1", artifactId = "artifact1", artifactVersion = "", commandId = "id", commandVersion = 
    "1.0.0", comments = "Some " + "comment")
public class AnnotationOverrideExample {
  
  //@CommandInfo(groupId = "group1", artifactId = "artifact1", commandId = "id", version = "2.0.0")
  static class AnnotationOverrideExampleOverridden extends AnnotationOverrideExample {
  
  }
  
  
  public static void main(String[] args) {
    CommandInfo annotation = AnnotationOverrideExampleOverridden.class.getAnnotation(CommandInfo.class);
    System.out.println(annotation);
  }
}
