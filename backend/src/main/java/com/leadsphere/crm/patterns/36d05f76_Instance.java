package com.leadsphere.crm.patterns;

public interface Instance {

  boolean isAlive();

  void setAlive(boolean alive);

  void onMessage(Message message);
}
