package com.leadsphere.crm.patterns;

import com.iluwatar.databus.DataType;
import com.iluwatar.databus.Member;
import com.iluwatar.databus.data.MessageData;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageCollectorMember implements Member {

  private final String name;

  private final List<String> messages = new ArrayList<>();

  public MessageCollectorMember(String name) {
    this.name = name;
  }

  @Override
  public void accept(final DataType data) {
    if (data instanceof MessageData) {
      handleEvent((MessageData) data);
    }
  }

  private void handleEvent(MessageData data) {
    LOGGER.info("{} sees message {}", name, data.getMessage());
    messages.add(data.getMessage());
  }

  public List<String> getMessages() {
    return List.copyOf(messages);
  }
}
