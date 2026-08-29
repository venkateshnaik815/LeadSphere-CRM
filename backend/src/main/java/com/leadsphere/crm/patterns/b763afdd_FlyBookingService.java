package com.leadsphere.crm.patterns;

public class FlyBookingService extends Service {
  public FlyBookingService(ServiceDiscoveryService service) {
    super(service);
  }

  @Override
  public String getName() {
    return "booking a Fly";
  }
}
