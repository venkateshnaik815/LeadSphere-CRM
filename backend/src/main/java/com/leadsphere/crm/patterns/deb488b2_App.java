package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class App {

  private App() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static void main(final String[] args) {
    ProductRepository productRepository = new InMemoryProductRepository();
    CartRepository cartRepository = new InMemoryCartRepository();
    OrderRepository orderRepository = new InMemoryOrderRepository();

    ShoppingCartService shoppingCartUseCase =
        new ShoppingCartService(productRepository, cartRepository, orderRepository);

    CartController cartController = new CartController(shoppingCartUseCase);
    OrderController orderController = new OrderController(shoppingCartUseCase);

    String userId = "user123";
    cartController.addItemToCart(userId, "1", 1);
    cartController.addItemToCart(userId, "2", 2);

    Order order = orderController.checkout(userId);
    LOGGER.info("Total: ${}", cartController.calculateTotal(userId));

    LOGGER.info(
        "Order placed! Order ID: {}, Total: ${}", order.getOrderId(), order.getTotalPrice());
  }
}
