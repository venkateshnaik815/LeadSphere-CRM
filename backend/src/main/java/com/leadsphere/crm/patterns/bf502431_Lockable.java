package com.leadsphere.crm.patterns;

import com.iluwatar.lockableobject.domain.Creature;

public interface Lockable {

  boolean isLocked();

  boolean lock(Creature creature);

  void unlock(Creature creature);

  Creature getLocker();

  String getName();
}
