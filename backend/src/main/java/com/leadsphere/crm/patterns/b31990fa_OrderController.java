package com.leadsphere.crm.patterns;

import com.iluwatar.monolithic.exceptions.InsufficientStockException;
import com.iluwatar.monolithic.exceptions.NonExistentProductException;
import com.iluwatar.monolithic.exceptions.NonExistentUserException;
import com.iluwatar.monolithic.model.Order;
import com.iluwatar.monolithic.model.Product;
import com.iluwatar.monolithic.model.User;
import com.iluwatar.monolithic.repository.OrderRepository;
import com.iluwatar.monolithic.repository.ProductRepository;
import com.iluwatar.monolithic.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderController {
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  public OrderController(
      OrderRepository orderRepository,
      UserRepository userRepository,
      ProductRepository productRepository) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
  }

  public Order placeOrder(Long userId, Long productId, Integer quantity) {
    final User user =
        userRepository
            .findById(userId)
            .orElseThrow(
                () -> new NonExistentUserException("User with ID " + userId + " not found"));

    final Product product =
        productRepository
            .findById(productId)
            .orElseThrow(
                () ->
                    new NonExistentProductException("Product with ID " + productId + " not found"));

    if (product.getStock() < quantity) {
      throw new InsufficientStockException("Not enough stock for product " + productId);
    }

    product.setStock(product.getStock() - quantity);
    productRepository.save(product);

    final Order order = new Order(null, user, product, quantity, product.getPrice() * quantity);
    return orderRepository.save(order);
  }
}
