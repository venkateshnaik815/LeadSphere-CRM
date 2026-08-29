package com.leadsphere.crm.patterns;

import com.iluwatar.commander.Service;
import com.iluwatar.commander.exceptions.DatabaseUnavailableException;
import lombok.RequiredArgsConstructor;

public class PaymentService extends Service {

  @RequiredArgsConstructor
  static class PaymentRequest {
    final String transactionId;
    final float payment;
    boolean paid;
  }

  public PaymentService(PaymentDatabase db, Exception... exc) {
    super(db, exc);
  }

  public String receiveRequest(Object... parameters) throws DatabaseUnavailableException {
    // it could also be sending an userid, payment details here or something, not added here
    var id = generateId();
    var req = new PaymentRequest(id, (float) parameters[0]);
    return updateDb(req);
  }

  protected String updateDb(Object... parameters) throws DatabaseUnavailableException {
    var req = (PaymentRequest) parameters[0];
    if (database.get(req.transactionId) == null || !req.paid) {
      database.add(req);
      req.paid = true;
      return req.transactionId;
    }
    return null;
  }
}
