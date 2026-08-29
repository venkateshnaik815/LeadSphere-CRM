package com.leadsphere.crm.patterns;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegacyShop {
  @Autowired private LegacyStore store;

  public void placeOrder(LegacyOrder legacyOrder) {
    store.put(legacyOrder.getId(), legacyOrder);
  }

  public Optional<LegacyOrder> findOrder(String orderId) {
    return store.get(orderId);
  }
}
