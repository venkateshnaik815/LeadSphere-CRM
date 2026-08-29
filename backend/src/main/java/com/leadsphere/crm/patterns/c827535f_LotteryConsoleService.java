package com.leadsphere.crm.patterns;

import com.iluwatar.hexagonal.banking.WireTransfers;
import com.iluwatar.hexagonal.domain.LotteryService;
import java.util.Scanner;

public interface LotteryConsoleService {

  void checkTicket(LotteryService service, Scanner scanner);

  void submitTicket(LotteryService service, Scanner scanner);

  void addFundsToLotteryAccount(WireTransfers bank, Scanner scanner);

  void queryLotteryAccountFunds(WireTransfers bank, Scanner scanner);
}
