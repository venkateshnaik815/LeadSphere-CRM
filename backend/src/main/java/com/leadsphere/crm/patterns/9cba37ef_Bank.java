
package com.leadsphere.crm.patterns;

import java.util.Arrays;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bank {

  @Getter private final int[] accounts;

  public Bank(int accountNum, int baseAmount) {
    accounts = new int[accountNum];
    Arrays.fill(accounts, baseAmount);
  }

  public synchronized void transfer(int accountA, int accountB, int amount) {
    if (accounts[accountA] >= amount && accountA != accountB) {
      accounts[accountB] += amount;
      accounts[accountA] -= amount;
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug(
            "Transferred from account: {} to account: {} , amount: {} , bank balance at: {}, source account balance: {}, destination account balance: {}",
            accountA,
            accountB,
            amount,
            getBalance(),
            getBalance(accountA),
            getBalance(accountB));
      }
    }
  }

  public synchronized int getBalance() {
    int balance = 0;
    for (int account : accounts) {
      balance += account;
    }
    return balance;
  }

  public synchronized int getBalance(int accountNumber) {
    return accounts[accountNumber];
  }
}
