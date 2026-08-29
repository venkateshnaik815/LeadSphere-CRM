package com.leadsphere.crm.patterns;

import com.iluwatar.specification.property.Color;
import com.iluwatar.specification.property.Mass;
import com.iluwatar.specification.property.Movement;
import com.iluwatar.specification.property.Size;

public class KillerBee extends AbstractCreature {

  public KillerBee() {
    this(new Mass(6.7));
  }

  public KillerBee(Mass mass) {
    super("KillerBee", Size.SMALL, Movement.FLYING, Color.LIGHT, mass);
  }
}
