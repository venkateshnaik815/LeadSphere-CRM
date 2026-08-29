
package com.leadsphere.crm.patterns;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderService {
  private final Map<Integer, Product> cart;

  public OrderService(Map<Integer, Product> cart) {
    this.cart = cart;
  }

  public void order() {
    Double total = getTotal();
    if (!this.cart.isEmpty()) {
      LOGGER.info(
          "Client has chosen to order {} with total {}", cart, String.format("%.2f", total));
      this.completeOrder();
    } else {
      LOGGER.info("Client's shopping cart is empty");
    }
  }

  public double getTotal() {
    final double[] total = {0.0};
    this.cart.forEach((key, product) -> total[0] += product.price());
    return total[0];
  }

  public void completeOrder() {
    this.cart.clear();
  }
}
