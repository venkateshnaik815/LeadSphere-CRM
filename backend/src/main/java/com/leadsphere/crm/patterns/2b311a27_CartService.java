
package com.leadsphere.crm.patterns;

import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CartService {
  @Getter private final Map<Integer, Product> cart;

  private final Map<Integer, Product> productCatalog;

  public CartService(Map<Integer, Product> cart, Map<Integer, Product> productCatalog) {
    this.cart = cart;
    this.productCatalog = productCatalog;
  }

  public void addToCart(int productId) {
    Product product = productCatalog.get(productId);
    if (product != null) {
      cart.put(productId, product);
      LOGGER.info("{} successfully added to the cart", product);
    } else {
      LOGGER.info("No product is found in catalog with id {}", productId);
    }
  }

  public void removeFromCart(int productId) {
    Product product = cart.remove(productId); // Remove product from cart
    if (product != null) {
      LOGGER.info("{} successfully removed from the cart", product);
    } else {
      LOGGER.info("No product is found in cart with id {}", productId);
    }
  }
}
