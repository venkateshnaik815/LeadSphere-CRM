package com.leadsphere.crm.patterns;

import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public abstract class DomainEvent implements Serializable {

  private final long sequenceId;
  private final long createdTime;
  private final String eventClassName;
  private boolean realTime = true;

  public abstract void process();
}
