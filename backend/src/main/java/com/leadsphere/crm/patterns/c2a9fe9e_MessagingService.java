package com.leadsphere.crm.patterns;

import com.iluwatar.commander.Service;
import com.iluwatar.commander.exceptions.DatabaseUnavailableException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessagingService extends Service {

  enum MessageToSend {
    PAYMENT_FAIL,
    PAYMENT_TRYING,
    PAYMENT_SUCCESSFUL
  }

  record MessageRequest(String reqId, MessageToSend msg) {}

  public MessagingService(MessagingDatabase db, Exception... exc) {
    super(db, exc);
  }

  public String receiveRequest(Object... parameters) throws DatabaseUnavailableException {
    var messageToSend = (int) parameters[0];
    var id = generateId();
    MessageToSend msg;
    if (messageToSend == 0) {
      msg = MessageToSend.PAYMENT_FAIL;
    } else if (messageToSend == 1) {
      msg = MessageToSend.PAYMENT_TRYING;
    } else { // messageToSend == 2
      msg = MessageToSend.PAYMENT_SUCCESSFUL;
    }
    var req = new MessageRequest(id, msg);
    return updateDb(req);
  }

  protected String updateDb(Object... parameters) throws DatabaseUnavailableException {
    var req = (MessageRequest) parameters[0];
    if (this.database.get(req.reqId) == null) { // idempotence, in case db fails here
      database.add(req); // if successful:
      LOGGER.info(sendMessage(req.msg));
      return req.reqId;
    }
    return null;
  }

  String sendMessage(MessageToSend m) {
    if (m.equals(MessageToSend.PAYMENT_SUCCESSFUL)) {
      return "Msg: Your order has been placed and paid for successfully!"
          + " Thank you for shopping with us!";
    } else if (m.equals(MessageToSend.PAYMENT_TRYING)) {
      return "Msg: There was an error in your payment process,"
          + " we are working on it and will return back to you shortly."
          + " Meanwhile, your order has been placed and will be shipped.";
    } else {
      return "Msg: There was an error in your payment process."
          + " Your order is placed and has been converted to COD."
          + " Please reach us on CUSTOMER-CARE-NUBER in case of any queries."
          + " Thank you for shopping with us!";
    }
  }
}
