
package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CakeBakingService;

public class CakeViewImpl implements View {

  private final CakeBakingService cakeBakingService;

  private static final Logger LOGGER = LoggerFactory.getLogger(CakeViewImpl.class);

  public CakeViewImpl(CakeBakingService cakeBakingService) {
    this.cakeBakingService = cakeBakingService;
  }

  @Override
  public void render() {
    cakeBakingService.getAllCakes().forEach(cake -> LOGGER.info(cake.toString()));
  }
}
