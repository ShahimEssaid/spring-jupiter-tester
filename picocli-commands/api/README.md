

# ICommands
* Is the registry for all the ICommandType instances that have been loaded/recognized.
* It holds the types by their path.
* Each path is a list of ICommandType. The first one on the list wins. This is intentional to avoid ignoring colliding command types (by path) so it can be detected if needed.
* It is loaded by various options.
  * Programmatically
  * ServiceLoader
  * Container initialization
  * etc
* The ICommands interface holds a map of named ICommand instances if needed as a central place to find named instances, if needed, but an application is free to manage an instance as it sees fit.
* 

# ICommandType
* Commands are registered with the ICommmands catalog with an instance of ICommandType. This is sort of a definition of a command that can be used later to instantiate, configure, and invoke a command.  It includes information like:
  * name: a default name
  * path: a catalog path. This path needs to be globally unique and not colliding with other command definitions.
  * factory: a factory instance that is an instance of IFactory
    * This is where the user object is created, and where interception is needed to enter an environment before creating/retrieving the user object.
  * title: a human-readable title.
  * short description:
  * long description:
  * command class: the user object's class to be instantiated when needed, by the factory.
  * 

# CommandLineConfig
* Represents a set of organized/configured commands to be operated on. It is built out of the available 