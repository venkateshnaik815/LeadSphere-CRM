package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.Map;

public class ApiGateway {

  // A map to store routes dynamically, where the key is the path and the value
  // is the associated FrontendComponent
  private final Map<String, FrontendComponent> routes = new HashMap<>();

  public void registerRoute(String path, FrontendComponent component) {
    routes.put(path, component);
  }

  public String handleRequest(String path, Map<String, String> params) {
    if (routes.containsKey(path)) {
      // Fetch data dynamically based on the provided parameters
      return routes.get(path).fetchData(params);
    } else {
      // Return a 404 error if the path is not registered
      return "404 Not Found";
    }
  }
}
