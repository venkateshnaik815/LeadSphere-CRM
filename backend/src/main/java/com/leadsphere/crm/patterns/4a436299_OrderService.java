package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
  private static final String ORDER_TOPIC = "order-topic";

  private final KafkaMessageProducer producer;

  public OrderService(KafkaMessageProducer producer) {
    this.producer = producer;
  }

  public void createOrder(String orderId) {
    LOGGER.info("Creating order: {}", orderId);
    Message message = new Message("Order Created: " + orderId);
    producer.publish(ORDER_TOPIC, message);
    LOGGER.info("Order creation message published for: {}", orderId);
  }

  public void updateOrder(String orderId) {
    LOGGER.info("Updating order: {}", orderId);
    Message message = new Message("Order Updated: " + orderId);
    producer.publish(ORDER_TOPIC, message);
    LOGGER.info("Order update message published for: {}", orderId);
  }

  public void cancelOrder(String orderId) {
    LOGGER.info("Cancelling order: {}", orderId);
    Message message = new Message("Order Cancelled: " + orderId);
    producer.publish(ORDER_TOPIC, message);
    LOGGER.info("Order cancellation message published for: {}", orderId);
  }
}
