package com.leadsphere.crm.patterns;

import com.iluwatar.specification.property.Color;
import com.iluwatar.specification.property.Mass;
import com.iluwatar.specification.property.Movement;
import com.iluwatar.specification.property.Size;

public class Troll extends AbstractCreature {

  public Troll() {
    this(new Mass(4000.0));
  }

  public Troll(Mass mass) {
    super("Troll", Size.LARGE, Movement.WALKING, Color.DARK, mass);
  }
}
