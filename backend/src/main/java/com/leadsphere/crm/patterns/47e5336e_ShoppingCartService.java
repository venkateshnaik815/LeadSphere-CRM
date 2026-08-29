package com.leadsphere.crm.patterns;

import java.util.List;

public class ShoppingCartService {
  private final ProductRepository productRepository;

  private final CartRepository cartRepository;

  private final OrderRepository orderRepository;

  public ShoppingCartService(
      final ProductRepository pdtRepository,
      final CartRepository repository,
      final OrderRepository ordRepository) {
    this.productRepository = pdtRepository;
    this.cartRepository = repository;
    this.orderRepository = ordRepository;
  }

  public void addItemToCart(final String userId, final String productId, final int quantity) {
    Product product = productRepository.getProductById(productId);
    if (product != null) {
      cartRepository.addItemToCart(userId, product, quantity);
    }
  }

  public void removeItemFromCart(final String userId, final String productId) {
    cartRepository.removeItemFromCart(userId, productId);
  }

  public double calculateTotal(final String userId) {
    return cartRepository.calculateTotal(userId);
  }

  public Order checkout(final String userId) {
    List<Cart> items = cartRepository.getItemsInCart(userId);
    String orderId = "ORDER-" + System.currentTimeMillis();
    Order order = new Order(orderId, items);
    orderRepository.saveOrder(order);
    cartRepository.clearCart(userId);
    return order;
  }
}
