package com.leadsphere.crm.patterns;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class TransportVehicle extends Vehicle {

  private int loadCapacity;

  protected TransportVehicle(String manufacturer, String model, int loadCapacity) {
    super(manufacturer, model);
    this.loadCapacity = loadCapacity;
  }
}
