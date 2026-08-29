package com.leadsphere.crm.patterns;

public class UnknownCommand implements Command {

  @Override
  public void process() {
    new ErrorView().display();
  }
}
