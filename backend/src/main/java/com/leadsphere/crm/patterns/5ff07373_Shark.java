package com.leadsphere.crm.patterns;

import com.iluwatar.specification.property.Color;
import com.iluwatar.specification.property.Mass;
import com.iluwatar.specification.property.Movement;
import com.iluwatar.specification.property.Size;

public class Shark extends AbstractCreature {

  public Shark() {
    this(new Mass(500.0));
  }

  public Shark(Mass mass) {
    super("Shark", Size.NORMAL, Movement.SWIMMING, Color.LIGHT, mass);
  }
}
