package com.leadsphere.crm.patterns;

public interface RequestHandler {

  boolean canHandleRequest(Request req);

  int getPriority();

  void handle(Request req);

  String name();
}
