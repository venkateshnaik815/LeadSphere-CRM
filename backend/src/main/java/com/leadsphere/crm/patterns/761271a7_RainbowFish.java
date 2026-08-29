package com.leadsphere.crm.patterns;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RainbowFish implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  private final String name;
  private final int age;
  private final int lengthMeters;
  private final int weightTons;
}
