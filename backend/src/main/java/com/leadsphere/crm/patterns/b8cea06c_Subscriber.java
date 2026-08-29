package com.leadsphere.crm.patterns;

import com.iluwatar.publish.subscribe.model.Message;

public interface Subscriber {

  void onMessage(Message message);
}
