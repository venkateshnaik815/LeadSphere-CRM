package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;

public class Bullet {

  @Getter @Setter private float position;

  public Bullet() {
    position = 0.0f;
  }
}
