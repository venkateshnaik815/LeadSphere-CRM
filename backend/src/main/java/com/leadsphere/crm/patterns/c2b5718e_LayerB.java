package com.leadsphere.crm.patterns;

import lombok.Getter;

@Getter
public class LayerB {

  private ServiceContext context;

  public LayerB(LayerA layerA) {
    this.context = layerA.getContext();
  }

  public void addSessionInfo(String sessionService) {
    context.setSessionService(sessionService);
  }
}
