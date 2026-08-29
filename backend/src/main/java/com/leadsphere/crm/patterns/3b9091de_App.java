package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    LOGGER.info("Use superpower: sky launch");
    var skyLaunch = new SkyLaunch();
    skyLaunch.activate();
    LOGGER.info("Use superpower: ground dive");
    var groundDive = new GroundDive();
    groundDive.activate();
  }
}
