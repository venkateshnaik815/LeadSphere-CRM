package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceImpl implements Service {

  private final String serviceName;
  private final int id;

  public ServiceImpl(String serviceName) {
    // set the service name
    this.serviceName = serviceName;

    // Generate a random id to this service object
    this.id = (int) Math.floor(Math.random() * 1000) + 1;
  }

  @Override
  public String getName() {
    return serviceName;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void execute() {
    LOGGER.info("Service {} is now executing with id {}", getName(), getId());
  }
}
