package com.leadsphere.crm.patterns;

import com.iluwatar.specification.creature.Creature;
import com.iluwatar.specification.property.Color;

public class ColorSelector extends AbstractSelector<Creature> {

  private final Color color;

  public ColorSelector(Color c) {
    this.color = c;
  }

  @Override
  public boolean test(Creature t) {
    return t.getColor().equals(color);
  }
}
