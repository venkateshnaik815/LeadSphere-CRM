package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.common.Dao;

public interface SpellbookDao extends Dao<Spellbook> {

  Spellbook findByName(String name);
}
