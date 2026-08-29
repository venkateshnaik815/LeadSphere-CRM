package com.leadsphere.crm.patterns;

import com.iluwatar.specification.creature.Creature;
import com.iluwatar.specification.property.Movement;

public class MovementSelector extends AbstractSelector<Creature> {

  private final Movement movement;

  public MovementSelector(Movement m) {
    this.movement = m;
  }

  @Override
  public boolean test(Creature t) {
    return t.getMovement().equals(movement);
  }
}
