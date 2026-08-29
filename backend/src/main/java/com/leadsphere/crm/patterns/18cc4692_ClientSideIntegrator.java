package com.leadsphere.crm.patterns;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientSideIntegrator {

  private final ApiGateway apiGateway;

  public ClientSideIntegrator(ApiGateway apiGateway) {
    this.apiGateway = apiGateway;
  }

  public void composeUi(String path, Map<String, String> params) {
    // Fetch data dynamically based on the route and parameters
    String data = apiGateway.handleRequest(path, params);
    LOGGER.info("Composed UI Component for path '" + path + "':");
    LOGGER.info(data);
  }
}
