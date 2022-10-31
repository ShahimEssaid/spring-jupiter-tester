package com.essaid.pcli.type;

public interface ICommandType extends Comparable<ICommandType> {
  
  /**
   * A globally unique identifier. The syntax is dot separated components with nesting allowed to indicate parent/child
   * command types. In other words, registering multiple {@link ICommandType} instances with
   * {@link ICommandTypeRegistry}, the assumption is that registrations with direct parent/child relationships based on
   * the id components will translate to direct parent/child relationships that could be (optionally) used to configure
   * a command line.  So, an {@link #getCommandTypeId()} of "some.group.name.a" is a parent type of the "some.group
   * .name.a.b" type.
   * <p>
   * <p>
   * This id value is not related to a command name. The command name will be taken from the
   * {@link picocli.CommandLine.Command} annotation and is optionally overridden later when a type is actually used to
   * configure a command line as is described elsewhere.
   *
   * @return
   */
  String getCommandTypeId();

//  /**
//   * If your command type needs a specific root command type when ran, specify this/these here. Otherwise, leave this
//   * empty/null indicate the lack of a dependency on a specific root command type.
//   *
//   * @return
//   */
//  String getRootCommandTypeId();
  
  int getOrder();
  
  
  Class<?> getCommandClass();
  
  
  /**
   * This can be an instance of {@link picocli.CommandLine.IFactory} or a {@link Class} object for a class that
   * implements it. If it is a class object, it will be instantiated with a best effort for constructor arguments.
   *
   * @return
   */
  Object getCommandFactory();
  
  /**
   * An instance or, or a class that implements, {@link picocli.CommandLine.IExecutionStrategy}.
   *
   * @return
   */
  Object getExecutionStrategy();
  
  /**
   * This is an opaque key that the registrant sets to help such a registrant avoid duplicate registrations. It could be
   * the {@link Class} object of the registrant or some other key of its choice. Basically, a registrant setting this
   * key and then checking for it should guarantee the lack of duplicate registrations. Registrants will likely be
   * triggered multiple times for the same {@link ICommandTypeRegistry} and they need to avoid duplicate registrations.
   *
   * @return
   */
  Object getRegistrationKey();
}


