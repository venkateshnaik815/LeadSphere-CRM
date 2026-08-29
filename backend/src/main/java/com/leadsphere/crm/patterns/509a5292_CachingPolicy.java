package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CachingPolicy {
  THROUGH("through"),
  AROUND("around"),
  BEHIND("behind"),
  ASIDE("aside");

  private final String policy;
}
