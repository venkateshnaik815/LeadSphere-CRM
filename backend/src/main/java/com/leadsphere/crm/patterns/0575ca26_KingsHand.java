package com.leadsphere.crm.patterns;

public class KingsHand extends EventEmitter implements EventObserver {

  public KingsHand() {}

  public KingsHand(EventObserver obs, Event e) {
    super(obs, e);
  }

  @Override
  public void onEvent(Event e) {
    notifyObservers(e);
  }

  @Override
  public void timePasses(Weekday day) {
    // This method is intentionally left empty because KingsHand does not handle time-based events
    // directly.
    // It serves as a placeholder to fulfill the EventObserver interface contract.
  }
}
