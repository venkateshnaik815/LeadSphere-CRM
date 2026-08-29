package com.leadsphere.crm.patterns;

import com.iluwatar.throttling.timer.Throttler;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Bartender {

  private static final Logger LOGGER = LoggerFactory.getLogger(Bartender.class);
  private final CallsCount callsCount;

  public Bartender(Throttler timer, CallsCount callsCount) {
    this.callsCount = callsCount;
    timer.start();
  }

  public int orderDrink(BarCustomer barCustomer) {
    var tenantName = barCustomer.getName();
    var count = callsCount.getCount(tenantName);
    if (count >= barCustomer.getAllowedCallsPerSecond()) {
      LOGGER.error("I'm sorry {}, you've had enough for today!", tenantName);
      return -1;
    }
    callsCount.incrementCount(tenantName);
    LOGGER.debug("Serving beer to {} : [{} consumed] ", barCustomer.getName(), count + 1);
    return getRandomCustomerId();
  }

  private int getRandomCustomerId() {
    return ThreadLocalRandom.current().nextInt(1, 10000);
  }
}
