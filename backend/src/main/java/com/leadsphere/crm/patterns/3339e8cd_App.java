package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
  public static void main(String[] args) throws Exception {
    GatewayFactory gatewayFactory = new GatewayFactory();

    // Register different gateways
    gatewayFactory.registerGateway("ServiceA", new ExternalServiceA());
    gatewayFactory.registerGateway("ServiceB", new ExternalServiceB());
    gatewayFactory.registerGateway("ServiceC", new ExternalServiceC());

    // Use an executor service for execution
    Gateway serviceA = gatewayFactory.getGateway("ServiceA");
    Gateway serviceB = gatewayFactory.getGateway("ServiceB");
    Gateway serviceC = gatewayFactory.getGateway("ServiceC");

    // Execute external services
    try {
      serviceA.execute();
      serviceB.execute();
      serviceC.execute();
    } catch (ThreadDeath e) {
      LOGGER.info("Interrupted!" + e);
      throw e;
    }
  }
}
