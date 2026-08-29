package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Cash {

  private int amount;

  // plus
  void plus(int addend) {
    amount += addend;
  }

  // minus
  boolean minus(int subtrahend) {
    if (amount >= subtrahend) {
      amount -= subtrahend;
      return true;
    } else {
      return false;
    }
  }

  // count
  int count() {
    return amount;
  }
}
