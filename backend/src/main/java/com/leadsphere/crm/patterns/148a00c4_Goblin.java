package com.leadsphere.crm.patterns;

import com.iluwatar.specification.property.Color;
import com.iluwatar.specification.property.Mass;
import com.iluwatar.specification.property.Movement;
import com.iluwatar.specification.property.Size;

public class Goblin extends AbstractCreature {

  public Goblin() {
    this(new Mass(30.0));
  }

  public Goblin(Mass mass) {
    super("Goblin", Size.SMALL, Movement.WALKING, Color.GREEN, mass);
  }
}
