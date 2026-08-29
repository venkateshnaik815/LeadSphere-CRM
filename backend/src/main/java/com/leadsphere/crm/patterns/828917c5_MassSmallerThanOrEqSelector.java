package com.leadsphere.crm.patterns;

import com.iluwatar.specification.creature.Creature;
import com.iluwatar.specification.property.Mass;

public class MassSmallerThanOrEqSelector extends AbstractSelector<Creature> {

  private final Mass mass;

  public MassSmallerThanOrEqSelector(double mass) {
    this.mass = new Mass(mass);
  }

  @Override
  public boolean test(Creature t) {
    return t.getMass().smallerThanOrEq(mass);
  }
}
