package com.leadsphere.crm.patterns;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public record FindCustomer(String customerId, Deque<BusinessException> errors)
    implements BusinessOperation<String> {
  public FindCustomer(String customerId, BusinessException... errors) {
    this(customerId, new ArrayDeque<>(List.of(errors)));
  }

  @Override
  public String perform() throws BusinessException {
    if (!this.errors.isEmpty()) {
      throw this.errors.pop();
    }

    return this.customerId;
  }
}
