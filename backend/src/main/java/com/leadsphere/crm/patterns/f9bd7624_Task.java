package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;

public class Task {

  @Getter private final int time;

  @Getter @Setter private boolean finished;

  public Task(int time) {
    this.time = time;
  }
}
