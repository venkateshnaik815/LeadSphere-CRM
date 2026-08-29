package com.leadsphere.crm.patterns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iluwatar.event.sourcing.state.AccountAggregate;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.Getter;

@Getter
public class MoneyDepositEvent extends DomainEvent {

  private final BigDecimal money;
  private final int accountNo;

  @JsonCreator
  public MoneyDepositEvent(
      @JsonProperty("sequenceId") long sequenceId,
      @JsonProperty("createdTime") long createdTime,
      @JsonProperty("accountNo") int accountNo,
      @JsonProperty("money") BigDecimal money) {
    super(sequenceId, createdTime, "MoneyDepositEvent");
    this.money = money;
    this.accountNo = accountNo;
  }

  @Override
  public void process() {
    var account =
        Optional.ofNullable(AccountAggregate.getAccount(accountNo))
            .orElseThrow(() -> new RuntimeException("Account not found"));
    account.handleEvent(this);
  }
}
