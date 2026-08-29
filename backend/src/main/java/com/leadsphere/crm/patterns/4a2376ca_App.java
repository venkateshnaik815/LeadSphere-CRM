package com.leadsphere.crm.patterns;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
  private static final String BOOTSTRAP_SERVERS = "localhost:9092";

  static long sleepMs = 2000;

  public static void main(String[] args) throws InterruptedException {
    KafkaMessageProducer producer = new KafkaMessageProducer(BOOTSTRAP_SERVERS);

    InventoryService inventoryService = new InventoryService();
    PaymentService paymentService = new PaymentService();
    NotificationService notificationService = new NotificationService();

    KafkaMessageConsumer inventoryConsumer =
        new KafkaMessageConsumer(
            BOOTSTRAP_SERVERS, "inventory-group", "order-topic", inventoryService::handleMessage);

    KafkaMessageConsumer paymentConsumer =
        new KafkaMessageConsumer(
            BOOTSTRAP_SERVERS, "payment-group", "order-topic", paymentService::handleMessage);

    KafkaMessageConsumer notificationConsumer =
        new KafkaMessageConsumer(
            BOOTSTRAP_SERVERS,
            "notification-group",
            "order-topic",
            notificationService::handleMessage);

    run(producer, inventoryConsumer, paymentConsumer, notificationConsumer);
  }

  static void run(
      KafkaMessageProducer producer,
      KafkaMessageConsumer inventoryConsumer,
      KafkaMessageConsumer paymentConsumer,
      KafkaMessageConsumer notificationConsumer)
      throws InterruptedException {
    LOGGER.info("Starting Microservices Messaging Pattern with Apache Kafka");

    // Start consumers in separate threads
    ExecutorService executor = Executors.newFixedThreadPool(3);
    executor.submit(inventoryConsumer);
    executor.submit(paymentConsumer);
    executor.submit(notificationConsumer);

    // Give consumers time to subscribe
    Thread.sleep(sleepMs);

    // Create producer service
    OrderService orderService = new OrderService(producer);

    // Demonstrate the messaging pattern
    LOGGER.info("\n=== Creating Order ===");
    orderService.createOrder("ORDER-001");

    Thread.sleep(sleepMs);

    LOGGER.info("\n=== Updating Order ===");
    orderService.updateOrder("ORDER-001");

    Thread.sleep(sleepMs);

    LOGGER.info("\n=== Cancelling Order ===");
    orderService.cancelOrder("ORDER-001");

    Thread.sleep(sleepMs);

    // Cleanup
    LOGGER.info("\nShutting down...");
    inventoryConsumer.stop();
    paymentConsumer.stop();
    notificationConsumer.stop();
    producer.close();

    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);

    LOGGER.info("Microservices Messaging Pattern demonstration completed");
  }
}
