package com.leadsphere.crm.patterns;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaintenanceLock {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintenanceLock.class);

  private static MaintenanceLock instance;

  @Getter private boolean lock = true;

  public static synchronized MaintenanceLock getInstance() {
    if (instance == null) {
      instance = new MaintenanceLock();
    }
    return instance;
  }

  public void setLock(boolean lock) {
    this.lock = lock;
    LOGGER.info("Maintenance lock is set to: {}", lock);
  }
}
