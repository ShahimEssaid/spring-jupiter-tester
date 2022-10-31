package com.essaid.pcli.config;

import com.essaid.pcli.type.ICommandType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import picocli.CommandLine;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommandTypeConfig {
  
  
  /**
   * The {@link ICommandType#getCommandTypeId()} for this type config.
   */
  private String commandTypeId;
  
  /**
   * The path/nesting at which a {@link CommandLine} will be added as a sub command to the parent/root CommandLine. This
   * should be dot separated and without any characters that are problematic for a shell or difficult to type by a user.
   *  The last segment of the path is the name that a user can use to invoke the command type. If you want to keep the
   * same name as in the {@link CommandLine.Command#name()} of the {@link ICommandType#getCommandClass()}, use that name
   * as the last segment of this path.
   */
  private String commandPath;
  
  /**
   * This can be an instance of {@link picocli.CommandLine.IFactory} or a {@link Class} object for a class that
   * implements it. If it is a class object, it will be instantiated with a best effort for constructor arguments.
   * <p>
   * <p>
   * This is optional and the one specified by {@link ICommandType#getCommandFactory()} will be used if this is null;
   */
  private Object commandFactory;
  
  /**
   * An instance or, or a class that implements, {@link picocli.CommandLine.IExecutionStrategy}.
   * <p>
   * This is optional and the one specified by {@link ICommandType#getExecutionStrategy()} will be used if this is
   * null;
   */
  private Object executionStrategy;
  
}
