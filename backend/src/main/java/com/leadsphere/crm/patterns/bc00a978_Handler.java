package com.leadsphere.crm.patterns;

public interface Handler<E extends Event> {

  void onEvent(E event);
}
