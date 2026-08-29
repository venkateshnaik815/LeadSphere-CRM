package com.leadsphere.crm.patterns;

import com.iluwatar.observer.WeatherType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenOrcs implements Race {

  @Override
  public void update(GenWeather weather, WeatherType weatherType) {
    LOGGER.info("The orcs are facing " + weatherType.getDescription() + " weather now");
  }
}
