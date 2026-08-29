package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiantModel {

  private Health health;
  private Fatigue fatigue;
  private Nourishment nourishment;

  @Override
  public String toString() {
    return String.format("The giant looks %s, %s and %s.", health, fatigue, nourishment);
  }
}
