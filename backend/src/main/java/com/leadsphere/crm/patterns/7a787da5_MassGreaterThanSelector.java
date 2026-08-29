package com.leadsphere.crm.patterns;

import com.iluwatar.specification.creature.Creature;
import com.iluwatar.specification.property.Mass;

public class MassGreaterThanSelector extends AbstractSelector<Creature> {

  private final Mass mass;

  public MassGreaterThanSelector(double mass) {
    this.mass = new Mass(mass);
  }

  @Override
  public boolean test(Creature t) {
    return t.getMass().greaterThan(mass);
  }
}
