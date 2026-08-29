package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Entity {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  protected int id;

  @Getter @Setter protected int position;

  public Entity(int id) {
    this.id = id;
    this.position = 0;
  }

  public abstract void update();
}
