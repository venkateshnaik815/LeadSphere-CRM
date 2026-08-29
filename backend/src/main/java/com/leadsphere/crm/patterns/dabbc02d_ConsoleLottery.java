package com.leadsphere.crm.patterns;

import com.google.inject.Guice;
import com.iluwatar.hexagonal.banking.WireTransfers;
import com.iluwatar.hexagonal.domain.LotteryService;
import com.iluwatar.hexagonal.module.LotteryModule;
import com.iluwatar.hexagonal.mongo.MongoConnectionPropertiesLoader;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleLottery {

  public static void main(String[] args) {
    MongoConnectionPropertiesLoader.load();
    var injector = Guice.createInjector(new LotteryModule());
    var service = injector.getInstance(LotteryService.class);
    var bank = injector.getInstance(WireTransfers.class);
    try (Scanner scanner = new Scanner(System.in)) {
      var exit = false;
      while (!exit) {
        printMainMenu();
        var cmd = readString(scanner);
        var lotteryConsoleService = new LotteryConsoleServiceImpl(LOGGER);
        if ("1".equals(cmd)) {
          lotteryConsoleService.queryLotteryAccountFunds(bank, scanner);
        } else if ("2".equals(cmd)) {
          lotteryConsoleService.addFundsToLotteryAccount(bank, scanner);
        } else if ("3".equals(cmd)) {
          lotteryConsoleService.submitTicket(service, scanner);
        } else if ("4".equals(cmd)) {
          lotteryConsoleService.checkTicket(service, scanner);
        } else if ("5".equals(cmd)) {
          exit = true;
        } else {
          LOGGER.info("Unknown command");
        }
      }
    }
  }

  private static void printMainMenu() {
    LOGGER.info("");
    LOGGER.info("### Lottery Service Console ###");
    LOGGER.info("(1) Query lottery account funds");
    LOGGER.info("(2) Add funds to lottery account");
    LOGGER.info("(3) Submit ticket");
    LOGGER.info("(4) Check ticket");
    LOGGER.info("(5) Exit");
  }

  private static String readString(Scanner scanner) {
    LOGGER.info("> ");
    return scanner.next();
  }
}
