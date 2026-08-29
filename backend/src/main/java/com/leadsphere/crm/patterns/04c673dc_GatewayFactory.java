package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.Map;

public class GatewayFactory {
  private Map<String, Gateway> gateways = new HashMap<>();

  public void registerGateway(String key, Gateway gateway) {
    gateways.put(key, gateway);
  }

  public Gateway getGateway(String key) {
    return gateways.get(key);
  }
}
