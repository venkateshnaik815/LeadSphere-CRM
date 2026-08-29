package com.leadsphere.crm.patterns;

import com.iluwatar.databus.AbstractDataType;
import com.iluwatar.databus.DataType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageData extends AbstractDataType {

  private final String message;

  public static DataType of(final String message) {
    return new MessageData(message);
  }
}
