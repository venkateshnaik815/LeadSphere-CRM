package com.leadsphere.crm.patterns;

import com.iluwatar.event.sourcing.event.DomainEvent;

public class DomainEventProcessor {

  private final EventJournal eventJournal;

  public DomainEventProcessor(EventJournal eventJournal) {
    this.eventJournal = eventJournal;
  }

  public void process(DomainEvent domainEvent) {
    domainEvent.process();
    eventJournal.write(domainEvent);
  }

  public void reset() {
    eventJournal.reset();
  }

  public void recover() {
    DomainEvent domainEvent;
    while ((domainEvent = eventJournal.readNext()) != null) {
      domainEvent.process();
    }
  }
}
