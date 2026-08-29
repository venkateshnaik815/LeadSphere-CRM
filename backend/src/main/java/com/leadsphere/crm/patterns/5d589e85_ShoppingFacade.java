
package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShoppingFacade {
  private final CartService cartService;
  private final OrderService orderService;
  private final PaymentService paymentService;

  public ShoppingFacade() {
    Map<Integer, Product> productCatalog = new HashMap<>();
    productCatalog.put(
        1, new Product(1, "Wireless Mouse", 25.99, "Ergonomic wireless mouse with USB receiver."));
    productCatalog.put(
        2,
        new Product(
            2, "Gaming Keyboard", 79.99, "RGB mechanical gaming keyboard with programmable keys."));
    Map<Integer, Product> cart = new HashMap<>();
    cartService = new CartService(cart, productCatalog);
    orderService = new OrderService(cart);
    paymentService = new PaymentService();
  }

  public Map<Integer, Product> getCart() {
    return this.cartService.getCart();
  }

  public void addToCart(int productId) {
    this.cartService.addToCart(productId);
  }

  public void removeFromCart(int productId) {
    this.cartService.removeFromCart(productId);
  }

  public void order() {
    this.orderService.order();
  }

  public Boolean isPaymentRequired() {
    double total = this.orderService.getTotal();
    if (total == 0.0) {
      LOGGER.info("No payment required");
      return false;
    }
    return true;
  }

  public void processPayment(String method) {
    Boolean isPaymentRequired = isPaymentRequired();
    if (Boolean.TRUE.equals(isPaymentRequired)) {
      paymentService.selectPaymentMethod(method);
    }
  }
}
