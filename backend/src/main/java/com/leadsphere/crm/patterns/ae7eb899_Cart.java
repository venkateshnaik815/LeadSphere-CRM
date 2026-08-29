package com.leadsphere.crm.patterns;

import lombok.Getter;

@Getter
public class Cart {
  private final Product product;

  private final int quantity;

  public Cart(final Product prod, final int qty) {
    this.product = prod;
    this.quantity = qty;
  }

  public double getTotalPrice() {
    return product.getPrice() * quantity;
  }
}
