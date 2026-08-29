package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LegacyOrder {
  private String id;
  private String customer;

  private String item;
  private int qty;
  private int price;
}
