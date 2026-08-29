package com.leadsphere.crm.patterns;

import com.iluwatar.event.sourcing.event.DomainEvent;
import java.io.File;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class EventJournal {

  File file;

  abstract void write(DomainEvent domainEvent);

  void reset() {
    if (file.delete()) {
      LOGGER.info("File cleared successfully............");
    }
  }

  abstract DomainEvent readNext();
}
