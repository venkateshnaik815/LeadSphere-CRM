package com.leadsphere.crm.patterns;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class PassengerVehicle extends Vehicle {

  private int noOfPassengers;

  protected PassengerVehicle(String manufacturer, String model, int noOfPassengers) {
    super(manufacturer, model);
    this.noOfPassengers = noOfPassengers;
  }
}
