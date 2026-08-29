package com.leadsphere.crm.patterns;

import lombok.Getter;

public enum WeatherType {
  SUNNY("Sunny"),
  RAINY("Rainy"),
  WINDY("Windy"),
  COLD("Cold");

  @Getter private final String description;

  WeatherType(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return this.name().toLowerCase();
  }
}
