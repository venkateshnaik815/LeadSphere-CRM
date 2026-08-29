package com.leadsphere.crm.patterns;

import com.iluwatar.bff.model.CartItem;
import com.iluwatar.bff.service.CartService;
import java.util.List;
import java.util.Map;

public final class InMemoryCartService implements CartService {

  private final Map<String, List<CartItem>> cartsByUserId;

  public InMemoryCartService(final Map<String, List<CartItem>> carts) {
    this.cartsByUserId = carts;
  }

  @Override
  public List<CartItem> getCart(final String userId) {
    return cartsByUserId.getOrDefault(userId, List.of());
  }
}
