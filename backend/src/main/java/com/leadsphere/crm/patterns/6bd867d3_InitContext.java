package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitContext {

  public Object lookup(String serviceName) {
    if (serviceName.equals("jndi/serviceA")) {
      LOGGER.info("Looking up service A and creating new service for A");
      return new ServiceImpl("jndi/serviceA");
    } else if (serviceName.equals("jndi/serviceB")) {
      LOGGER.info("Looking up service B and creating new service for B");
      return new ServiceImpl("jndi/serviceB");
    } else {
      return null;
    }
  }
}
