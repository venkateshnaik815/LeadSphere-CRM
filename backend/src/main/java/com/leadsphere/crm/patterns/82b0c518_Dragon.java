package com.leadsphere.crm.patterns;

import com.iluwatar.specification.property.Color;
import com.iluwatar.specification.property.Mass;
import com.iluwatar.specification.property.Movement;
import com.iluwatar.specification.property.Size;

public class Dragon extends AbstractCreature {

  public Dragon() {
    this(new Mass(39300.0));
  }

  public Dragon(Mass mass) {
    super("Dragon", Size.LARGE, Movement.FLYING, Color.RED, mass);
  }
}
