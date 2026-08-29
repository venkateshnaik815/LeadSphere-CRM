package com.leadsphere.crm.patterns;

import lombok.Getter;

@Getter
public class Product {
  private final String id;

  private final String name;

  private final double price;

  public Product(final String pdtId, final String firstName, final double p) {
    this.id = pdtId;
    this.name = firstName;
    this.price = p;
  }
}
