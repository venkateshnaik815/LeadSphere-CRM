package com.leadsphere.crm.patterns;

import com.iluwatar.commander.Database;
import com.iluwatar.commander.shippingservice.ShippingService.ShippingRequest;
import java.util.Hashtable;
import java.util.Map;

public class ShippingDatabase extends Database<ShippingRequest> {

  private final Map<String, ShippingRequest> data = new Hashtable<>();

  @Override
  public ShippingRequest add(ShippingRequest r) {
    return data.put(r.transactionId, r);
  }

  public ShippingRequest get(String trasnactionId) {
    return data.get(trasnactionId);
  }
}
