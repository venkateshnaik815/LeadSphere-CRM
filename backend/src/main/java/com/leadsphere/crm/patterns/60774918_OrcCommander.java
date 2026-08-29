package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrcCommander implements RequestHandler {
  @Override
  public boolean canHandleRequest(Request req) {
    return req.getRequestType() == RequestType.DEFEND_CASTLE;
  }

  @Override
  public int getPriority() {
    return 2;
  }

  @Override
  public void handle(Request req) {
    req.markHandled();
    LOGGER.info("{} handling request \"{}\"", name(), req);
  }

  @Override
  public String name() {
    return "Orc commander";
  }
}
