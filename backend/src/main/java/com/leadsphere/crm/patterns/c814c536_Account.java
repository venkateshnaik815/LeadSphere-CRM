package com.leadsphere.crm.patterns;

import com.iluwatar.event.sourcing.event.AccountCreateEvent;
import com.iluwatar.event.sourcing.event.MoneyDepositEvent;
import com.iluwatar.event.sourcing.event.MoneyTransferEvent;
import com.iluwatar.event.sourcing.state.AccountAggregate;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@RequiredArgsConstructor
@Slf4j
public class Account {

  private final int accountNo;
  private final String owner;
  private BigDecimal money = BigDecimal.ZERO;

  private static final String MSG =
      "Some external api for only realtime execution could be called here.";

  public Account copy() {
    var account = new Account(accountNo, owner);
    account.setMoney(money);
    return account;
  }

  @Override
  public String toString() {
    return "Account{"
        + "accountNo="
        + accountNo
        + ", owner='"
        + owner
        + '\''
        + ", money="
        + money
        + '}';
  }

  private void depositMoney(BigDecimal money) {
    this.money = this.money.add(money);
  }

  private void withdrawMoney(BigDecimal money) {
    this.money = this.money.subtract(money);
  }

  private void handleDeposit(BigDecimal money, boolean realTime) {
    depositMoney(money);
    AccountAggregate.putAccount(this);
    if (realTime) {
      LOGGER.info(MSG);
    }
  }

  private void handleWithdrawal(BigDecimal money, boolean realTime) {
    if (this.money.compareTo(money) < 0) {
      throw new RuntimeException("Insufficient Account Balance");
    }

    withdrawMoney(money);
    AccountAggregate.putAccount(this);
    if (realTime) {
      LOGGER.info(MSG);
    }
  }

  public void handleEvent(MoneyDepositEvent moneyDepositEvent) {
    handleDeposit(moneyDepositEvent.getMoney(), moneyDepositEvent.isRealTime());
  }

  public void handleEvent(AccountCreateEvent accountCreateEvent) {
    AccountAggregate.putAccount(this);
    if (accountCreateEvent.isRealTime()) {
      LOGGER.info(MSG);
    }
  }

  public void handleTransferFromEvent(MoneyTransferEvent moneyTransferEvent) {
    handleWithdrawal(moneyTransferEvent.getMoney(), moneyTransferEvent.isRealTime());
  }

  public void handleTransferToEvent(MoneyTransferEvent moneyTransferEvent) {
    handleDeposit(moneyTransferEvent.getMoney(), moneyTransferEvent.isRealTime());
  }
}
