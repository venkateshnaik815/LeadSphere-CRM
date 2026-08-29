package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  private static final String SERVICE = "SERVICE";

  public static void main(String[] args) {
    // Initiate first layer and add service information into context
    var layerA = new LayerA();
    layerA.addAccountInfo(SERVICE);

    logContext(layerA.getContext());

    // Initiate second layer and preserving information retrieved in first layer through passing
    // context object
    var layerB = new LayerB(layerA);
    layerB.addSessionInfo(SERVICE);

    logContext(layerB.getContext());

    // Initiate third layer and preserving information retrieved in first and second layer through
    // passing context object
    var layerC = new LayerC(layerB);
    layerC.addSearchInfo(SERVICE);

    logContext(layerC.getContext());
  }

  private static void logContext(ServiceContext context) {
    LOGGER.info("Context = {}", context);
  }
}
