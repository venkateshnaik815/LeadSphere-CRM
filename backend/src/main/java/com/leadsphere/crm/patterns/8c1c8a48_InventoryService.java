package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryService {
  private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

  public void handleMessage(Message message) {
    LOGGER.info(
        "Inventory Service received message [{}]: {}", message.getId(), message.getContent());

    if (message.getContent().contains("Order Created")) {
      updateInventory(message);
    } else if (message.getContent().contains("Order Cancelled")) {
      restoreInventory(message);
    } else {
      LOGGER.debug("No inventory action needed for: {}", message.getContent());
    }
  }

  private void updateInventory(Message message) {
    LOGGER.info("Updating inventory for message: {}", message.getId());
    // Simulate inventory update - reserve stock for the order
    try {
      Thread.sleep(100);
      LOGGER.info("Inventory updated successfully for: {}", message.getContent());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      LOGGER.error("Inventory update interrupted", e);
    }
  }

  private void restoreInventory(Message message) {
    LOGGER.info("Restoring inventory for message: {}", message.getId());
    // Simulate inventory restoration - release reserved stock
    try {
      Thread.sleep(100);
      LOGGER.info("Inventory restored successfully for: {}", message.getContent());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      LOGGER.error("Inventory restore interrupted", e);
    }
  }
}
