package com.leadsphere.crm.patterns;

public class OrderController {
  private final ShoppingCartService shoppingCartUseCase;

  public OrderController(final ShoppingCartService shoppingCartUse) {
    this.shoppingCartUseCase = shoppingCartUse;
  }

  public Order checkout(final String userId) {
    return shoppingCartUseCase.checkout(userId);
  }
}
