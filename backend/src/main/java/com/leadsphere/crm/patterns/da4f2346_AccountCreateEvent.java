package com.leadsphere.crm.patterns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iluwatar.event.sourcing.domain.Account;
import com.iluwatar.event.sourcing.state.AccountAggregate;
import lombok.Getter;

@Getter
public class AccountCreateEvent extends DomainEvent {

  private final int accountNo;
  private final String owner;

  @JsonCreator
  public AccountCreateEvent(
      @JsonProperty("sequenceId") long sequenceId,
      @JsonProperty("createdTime") long createdTime,
      @JsonProperty("accountNo") int accountNo,
      @JsonProperty("owner") String owner) {
    super(sequenceId, createdTime, "AccountCreateEvent");
    this.accountNo = accountNo;
    this.owner = owner;
  }

  @Override
  public void process() {
    var account = AccountAggregate.getAccount(accountNo);
    if (account != null) {
      throw new RuntimeException("Account already exists");
    }
    account = new Account(accountNo, owner);
    account.handleEvent(this);
  }
}
