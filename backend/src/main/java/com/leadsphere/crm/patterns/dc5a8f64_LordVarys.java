package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LordVarys extends EventEmitter implements EventObserver {

  public LordVarys() {}

  public LordVarys(EventObserver obs, Event e) {
    super(obs, e);
  }

  @Override
  public void timePasses(Weekday day) {
    if (day == Weekday.SATURDAY) {
      notifyObservers(Event.TRAITOR_DETECTED);
    }
  }

  @Override
  public void onEvent(Event e) {
    notifyObservers(e);
  }
}
