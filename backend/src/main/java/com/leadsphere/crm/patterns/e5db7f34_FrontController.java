package com.leadsphere.crm.patterns;

public class FrontController {

  private final Dispatcher dispatcher;

  public FrontController() {
    this.dispatcher = new Dispatcher();
  }

  public void handleRequest(String request) {
    dispatcher.dispatch(request);
  }
}
