package com.leadsphere.crm.patterns;

public class CartController {

  private final ShoppingCartService shoppingCartUseCase;

  public CartController(final ShoppingCartService shoppingCart) {
    this.shoppingCartUseCase = shoppingCart;
  }

  public void addItemToCart(final String userId, final String productId, final int quantity) {
    shoppingCartUseCase.addItemToCart(userId, productId, quantity);
  }

  public void removeItemFromCart(final String userId, final String productId) {
    shoppingCartUseCase.removeItemFromCart(userId, productId);
  }

  public double calculateTotal(final String userId) {
    return shoppingCartUseCase.calculateTotal(userId);
  }
}
