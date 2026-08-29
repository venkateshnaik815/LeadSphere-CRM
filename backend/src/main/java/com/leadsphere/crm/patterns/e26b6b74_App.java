package com.leadsphere.crm.patterns;

import com.iluwatar.observer.generic.GenHobbits;
import com.iluwatar.observer.generic.GenOrcs;
import com.iluwatar.observer.generic.GenWeather;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    var weather = new Weather();
    weather.addObserver(new Orcs());
    weather.addObserver(new Hobbits());

    weather.timePasses();
    weather.timePasses();
    weather.timePasses();
    weather.timePasses();

    // Generic observer inspired by Java Generics and Collections by Naftalin & Wadler
    LOGGER.info("--Running generic version--");
    var genericWeather = new GenWeather();
    genericWeather.addObserver(new GenOrcs());
    genericWeather.addObserver(new GenHobbits());

    genericWeather.timePasses();
    genericWeather.timePasses();
    genericWeather.timePasses();
    genericWeather.timePasses();
  }
}
