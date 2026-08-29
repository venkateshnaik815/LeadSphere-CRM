package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NetflixService implements VideoStreamingService {

  @Override
  public void doProcessing() {
    LOGGER.info("NetflixService is now processing");
  }
}
