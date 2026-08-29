package com.leadsphere.crm.patterns;

import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WashingMachine {

  private final DelayProvider delayProvider;

  @Getter private WashingMachineState washingMachineState;

  public WashingMachine() {
    this(
        (interval, timeUnit, task) -> {
          try {
            Thread.sleep(timeUnit.toMillis(interval));
          } catch (InterruptedException ie) {
            LOGGER.error("", ie);
            Thread.currentThread().interrupt();
          }
          task.run();
        });
  }

  public WashingMachine(DelayProvider delayProvider) {
    this.delayProvider = delayProvider;
    this.washingMachineState = WashingMachineState.ENABLED;
  }

  public void wash() {
    synchronized (this) {
      var machineState = getWashingMachineState();
      LOGGER.info("{}: Actual machine state: {}", Thread.currentThread().getName(), machineState);
      if (this.washingMachineState == WashingMachineState.WASHING) {
        LOGGER.error("Cannot wash if the machine has been already washing!");
        return;
      }
      this.washingMachineState = WashingMachineState.WASHING;
    }
    LOGGER.info("{}: Doing the washing", Thread.currentThread().getName());

    this.delayProvider.executeAfterDelay(50, TimeUnit.MILLISECONDS, this::endOfWashing);
  }

  public synchronized void endOfWashing() {
    washingMachineState = WashingMachineState.ENABLED;
    LOGGER.info("{}: Washing completed.", Thread.currentThread().getId());
  }
}
