package com.leadsphere.crm.patterns;

import com.iluwatar.databus.DataType;
import com.iluwatar.databus.Member;
import com.iluwatar.databus.data.MessageData;
import com.iluwatar.databus.data.StartingData;
import com.iluwatar.databus.data.StoppingData;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@RequiredArgsConstructor
public class StatusMember implements Member {

  private final int id;

  private LocalDateTime started;

  private LocalDateTime stopped;

  @Override
  public void accept(final DataType data) {
    if (data instanceof StartingData) {
      handleEvent((StartingData) data);
    } else if (data instanceof StoppingData) {
      handleEvent((StoppingData) data);
    }
  }

  private void handleEvent(StartingData data) {
    started = data.getWhen();
    LOGGER.info("Receiver {} sees application started at {}", id, started);
  }

  private void handleEvent(StoppingData data) {
    stopped = data.getWhen();
    LOGGER.info("Receiver {} sees application stopping at {}", id, stopped);
    LOGGER.info("Receiver {} sending goodbye message", id);
    data.getDataBus().publish(MessageData.of(String.format("Goodbye cruel world from #%d!", id)));
  }
}
