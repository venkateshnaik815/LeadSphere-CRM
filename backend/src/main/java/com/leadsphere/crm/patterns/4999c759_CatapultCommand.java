package com.leadsphere.crm.patterns;

public class CatapultCommand implements Command {

  @Override
  public void process() {
    new CatapultView().display();
  }
}
