package com.leadsphere.crm.patterns;

public final class ServiceLocator {

  private static final ServiceCache serviceCache = new ServiceCache();

  private ServiceLocator() {}

  public static Service getService(String serviceJndiName) {
    var serviceObj = serviceCache.getService(serviceJndiName);
    if (serviceObj == null) {
      var ctx = new InitContext();
      serviceObj = (Service) ctx.lookup(serviceJndiName);
      if (serviceObj != null) { // Only cache a service if it actually exists
        serviceCache.addService(serviceObj);
      }
    }
    return serviceObj;
  }
}
