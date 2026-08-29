package com.leadsphere.crm.patterns;

import com.iluwatar.observer.WeatherType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenWeather extends Observable<GenWeather, Race, WeatherType> {

  private WeatherType currentWeather;

  public GenWeather() {
    currentWeather = WeatherType.SUNNY;
  }

  public void timePasses() {
    var enumValues = WeatherType.values();
    currentWeather = enumValues[(currentWeather.ordinal() + 1) % enumValues.length];
    LOGGER.info("The weather changed to {}.", currentWeather);
    notifyObservers(currentWeather);
  }
}
