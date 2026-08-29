package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceCache {

  private final Map<String, Service> serviceCache;

  public ServiceCache() {
    serviceCache = new HashMap<>();
  }

  public Service getService(String serviceName) {
    if (serviceCache.containsKey(serviceName)) {
      var cachedService = serviceCache.get(serviceName);
      var name = cachedService.getName();
      var id = cachedService.getId();
      LOGGER.info("(cache call) Fetched service {}({}) from cache... !", name, id);
      return cachedService;
    }
    return null;
  }

  public void addService(Service newService) {
    serviceCache.put(newService.getName(), newService);
  }
}
