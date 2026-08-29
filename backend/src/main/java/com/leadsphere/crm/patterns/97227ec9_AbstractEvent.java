package com.leadsphere.crm.patterns;

import com.iluwatar.eda.framework.Event;
import com.iluwatar.eda.framework.EventDispatcher;

public abstract class AbstractEvent implements Event {

  public Class<? extends Event> getType() {
    return getClass();
  }
}
