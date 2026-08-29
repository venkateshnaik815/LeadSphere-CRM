package com.leadsphere.crm.patterns;

import com.iluwatar.specification.property.Color;
import com.iluwatar.specification.property.Mass;
import com.iluwatar.specification.property.Movement;
import com.iluwatar.specification.property.Size;

public class Octopus extends AbstractCreature {

  public Octopus() {
    this(new Mass(12.0));
  }

  public Octopus(Mass mass) {
    super("Octopus", Size.NORMAL, Movement.SWIMMING, Color.DARK, mass);
  }
}
