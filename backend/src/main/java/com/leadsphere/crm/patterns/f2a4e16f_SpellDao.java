package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.common.Dao;

public interface SpellDao extends Dao<Spell> {

  Spell findByName(String name);
}
