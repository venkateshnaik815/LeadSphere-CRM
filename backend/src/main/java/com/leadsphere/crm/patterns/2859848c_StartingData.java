package com.leadsphere.crm.patterns;

import com.iluwatar.databus.AbstractDataType;
import com.iluwatar.databus.DataType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class StartingData extends AbstractDataType {

  private final LocalDateTime when;

  public static DataType of(final LocalDateTime when) {
    return new StartingData(when);
  }
}
