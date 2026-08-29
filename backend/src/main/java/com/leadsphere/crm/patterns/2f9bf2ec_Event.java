package com.leadsphere.crm.patterns;

public interface Event {

  Class<? extends Event> getType();
}
