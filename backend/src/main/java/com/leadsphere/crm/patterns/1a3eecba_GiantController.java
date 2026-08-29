package com.leadsphere.crm.patterns;

public class GiantController {

  public Dispatcher dispatcher;

  public GiantController(Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  public void setCommand(Command s, int index) {
    dispatcher.performAction(s, index);
  }

  public void updateView(GiantModel giantModel) {
    dispatcher.updateView(giantModel);
  }
}
