package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Shipment {
  private String item;
  private int qty;
  private int price;
}
