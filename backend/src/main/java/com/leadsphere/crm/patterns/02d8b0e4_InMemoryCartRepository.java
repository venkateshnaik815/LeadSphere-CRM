package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCartRepository implements CartRepository {
  private final Map<String, List<Cart>> userCarts = new HashMap<>();

  @Override
  public void addItemToCart(final String userId, final Product product, final int quantity) {
    List<Cart> cart = userCarts.getOrDefault(userId, new ArrayList<>());
    cart.add(new Cart(product, quantity));
    userCarts.put(userId, cart);
  }

  @Override
  public void removeItemFromCart(final String userId, final String productId) {
    List<Cart> cart = userCarts.get(userId);
    if (cart != null) {
      cart.removeIf(item -> item.getProduct().getId().equals(productId));
    }
  }

  @Override
  public List<Cart> getItemsInCart(final String userId) {
    return userCarts.getOrDefault(userId, new ArrayList<>());
  }

  @Override
  public double calculateTotal(final String userId) {
    return userCarts.getOrDefault(userId, new ArrayList<>()).stream()
        .mapToDouble(Cart::getTotalPrice)
        .sum();
  }

  @Override
  public void clearCart(final String userId) {
    userCarts.remove(userId);
  }
}
