package com.leadsphere.crm.patterns;

public class Dispatcher {

  public void dispatch(String request) {
    var command = getCommand(request);
    command.process();
  }

  Command getCommand(String request) {
    var commandClass = getCommandClass(request);
    try {
      return (Command) commandClass.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new ApplicationException(e);
    }
  }

  static Class<?> getCommandClass(String request) {
    try {
      return Class.forName("com.iluwatar.front.controller." + request + "Command");
    } catch (ClassNotFoundException e) {
      return UnknownCommand.class;
    }
  }
}
