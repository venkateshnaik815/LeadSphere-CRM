package com.leadsphere.crm.patterns;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Event {
  WHITE_WALKERS_SIGHTED("White walkers sighted"),
  STARK_SIGHTED("Stark sighted"),
  WARSHIPS_APPROACHING("Warships approaching"),
  TRAITOR_DETECTED("Traitor detected");

  private final String description;

  @Override
  public String toString() {
    return description;
  }
}
