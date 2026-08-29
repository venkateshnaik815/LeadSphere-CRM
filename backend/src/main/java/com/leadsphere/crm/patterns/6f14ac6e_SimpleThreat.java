package com.leadsphere.crm.patterns;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class SimpleThreat implements Threat {

  private final ThreatType threatType;
  private final int id;
  private final String name;

  @Override
  public String name() {
    return name;
  }

  @Override
  public int id() {
    return id;
  }

  @Override
  public ThreatType type() {
    return threatType;
  }
}
