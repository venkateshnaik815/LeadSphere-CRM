package com.leadsphere.crm.patterns;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "TRAIN")
public class Train extends PassengerVehicle {

  private int noOfCarriages;

  public Train(String manufacturer, String model, int noOfPassengers, int noOfCarriages) {
    super(manufacturer, model, noOfPassengers);
    this.noOfCarriages = noOfCarriages;
  }

  // Overridden the toString method to specify the Vehicle object
  @Override
  public String toString() {
    return "Train{" + super.toString() + '}';
  }
}
