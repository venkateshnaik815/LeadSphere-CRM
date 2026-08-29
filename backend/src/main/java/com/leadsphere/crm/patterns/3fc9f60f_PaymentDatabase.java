package com.leadsphere.crm.patterns;

import com.iluwatar.commander.Database;
import com.iluwatar.commander.paymentservice.PaymentService.PaymentRequest;
import java.util.Hashtable;
import java.util.Map;

public class PaymentDatabase extends Database<PaymentRequest> {

  // 0-fail, 1-error, 2-success
  private final Map<String, PaymentRequest> data = new Hashtable<>();

  @Override
  public PaymentRequest add(PaymentRequest r) {
    return data.put(r.transactionId, r);
  }

  @Override
  public PaymentRequest get(String requestId) {
    return data.get(requestId);
  }
}
