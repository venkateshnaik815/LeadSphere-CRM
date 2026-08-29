package com.leadsphere.crm.patterns;

import com.google.inject.Guice;
import com.iluwatar.hexagonal.domain.LotteryAdministration;
import com.iluwatar.hexagonal.domain.LotteryService;
import com.iluwatar.hexagonal.module.LotteryTestingModule;
import com.iluwatar.hexagonal.sampledata.SampleData;

public class App {

  public static void main(String[] args) {

    var injector = Guice.createInjector(new LotteryTestingModule());

    // start new lottery round
    var administration = injector.getInstance(LotteryAdministration.class);
    administration.resetLottery();

    // submit some lottery tickets
    var service = injector.getInstance(LotteryService.class);
    SampleData.submitTickets(service, 20);

    // perform lottery
    administration.performLottery();
  }
}
