package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YouTubeService implements VideoStreamingService {

  @Override
  public void doProcessing() {
    LOGGER.info("YouTubeService is now processing");
  }
}
