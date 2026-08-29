package com.leadsphere.crm.patterns;

import com.iluwatar.specification.property.Color;
import com.iluwatar.specification.property.Mass;
import com.iluwatar.specification.property.Movement;
import com.iluwatar.specification.property.Size;

public interface Creature {

  String getName();

  Size getSize();

  Movement getMovement();

  Color getColor();

  Mass getMass();
}
