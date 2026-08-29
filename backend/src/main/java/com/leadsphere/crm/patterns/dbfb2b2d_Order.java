package com.leadsphere.crm.patterns;

import java.util.List;
import lombok.Getter;

@Getter
public class Order {
  private final String orderId;

  private final List<Cart> items;

  private final double totalPrice;

  public Order(final String id, final List<Cart> item) {
    this.orderId = id;
    this.items = item;
    this.totalPrice = items.stream().mapToDouble(Cart::getTotalPrice).sum();
  }
}
