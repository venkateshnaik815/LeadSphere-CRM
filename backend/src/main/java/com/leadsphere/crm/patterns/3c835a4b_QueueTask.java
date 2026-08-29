package com.leadsphere.crm.patterns;

import com.iluwatar.commander.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class QueueTask {

  public enum TaskType {
    MESSAGING,
    PAYMENT,
    EMPLOYEE_DB
  }

  public final Order order;
  public final TaskType taskType;
  public final int messageType; // 0-fail, 1-error, 2-success

  @Getter @Setter private long firstAttemptTime = -1L; // when first time attempt made to do task

  public String getType() {
    if (!this.taskType.equals(TaskType.MESSAGING)) {
      return this.taskType.toString();
    } else {
      if (this.messageType == 0) {
        return "Payment Failure Message";
      } else if (this.messageType == 1) {
        return "Payment Error Message";
      } else {
        return "Payment Success Message";
      }
    }
  }

  public boolean isFirstAttempt() {
    return this.firstAttemptTime == -1L;
  }
}
