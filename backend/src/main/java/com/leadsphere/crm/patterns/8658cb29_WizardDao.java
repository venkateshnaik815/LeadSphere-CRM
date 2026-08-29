package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.common.Dao;

public interface WizardDao extends Dao<Wizard> {

  Wizard findByName(String name);
}
