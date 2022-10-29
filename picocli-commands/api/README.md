
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