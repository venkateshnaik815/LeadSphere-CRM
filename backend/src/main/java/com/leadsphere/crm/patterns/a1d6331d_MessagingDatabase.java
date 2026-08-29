package com.leadsphere.crm.patterns;

import com.iluwatar.commander.Database;
import com.iluwatar.commander.messagingservice.MessagingService.MessageRequest;
import java.util.Hashtable;
import java.util.Map;

public class MessagingDatabase extends Database<MessageRequest> {
  private final Map<String, MessageRequest> data = new Hashtable<>();

  @Override
  public MessageRequest add(MessageRequest r) {
    return data.put(r.reqId(), r);
  }

  @Override
  public MessageRequest get(String requestId) {
    return data.get(requestId);
  }
}
