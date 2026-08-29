package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Room {

  private int id;
  private String roomType;
  private int price;
  private boolean booked;
}
