package com.leadsphere.crm.patterns;

import com.iluwatar.corruption.system.legacy.LegacyShop;
import com.iluwatar.corruption.system.modern.Customer;
import com.iluwatar.corruption.system.modern.ModernOrder;
import com.iluwatar.corruption.system.modern.Shipment;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AntiCorruptionLayer {

  @Autowired private LegacyShop legacyShop;

  public Optional<ModernOrder> findOrderInLegacySystem(String id) {

    return legacyShop
        .findOrder(id)
        .map(
            o ->
                new ModernOrder(
                    o.getId(),
                    new Customer(o.getCustomer()),
                    new Shipment(o.getItem(), o.getQty(), o.getPrice()),
                    ""));
  }
}
