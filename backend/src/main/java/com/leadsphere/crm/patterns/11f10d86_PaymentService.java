package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

  public void handleMessage(Message message) {
    LOGGER.info("Payment Service received message [{}]: {}", message.getId(), message.getContent());

    if (message.getContent().contains("Order Created")) {
      processPayment(message);
    } else if (message.getContent().contains("Order Cancelled")) {
      refundPayment(message);
    } else {
      LOGGER.debug("No payment action needed for: {}", message.getContent());
    }
  }

  private void processPayment(Message message) {
    LOGGER.info("Processing payment for message: {}", message.getId());
    // Simulate payment processing - charge the customer
    try {
      Thread.sleep(150);
      LOGGER.info("Payment processed successfully for: {}", message.getContent());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      LOGGER.error("Payment processing interrupted", e);
    }
  }

  private void refundPayment(Message message) {
    LOGGER.info("Refunding payment for message: {}", message.getId());
    // Simulate payment refund - return money to customer
    try {
      Thread.sleep(150);
      LOGGER.info("Payment refunded successfully for: {}", message.getContent());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      LOGGER.error("Payment refund interrupted", e);
    }
  }
}
