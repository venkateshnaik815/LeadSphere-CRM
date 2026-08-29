package com.leadsphere.crm.patterns;

import com.iluwatar.specification.creature.Creature;
import com.iluwatar.specification.property.Mass;

public class MassEqualSelector extends AbstractSelector<Creature> {

  private final Mass mass;

  public MassEqualSelector(double mass) {
    this.mass = new Mass(mass);
  }

  @Override
  public boolean test(Creature t) {
    return t.getMass().equals(mass);
  }
}
