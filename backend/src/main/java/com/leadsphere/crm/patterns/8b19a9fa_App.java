package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    var pool = new OliphauntPool();
    LOGGER.info(pool.toString());
    var oliphaunt1 = pool.checkOut();
    String checkedOut = "Checked out {}";

    LOGGER.info(checkedOut, oliphaunt1);
    LOGGER.info(pool.toString());
    var oliphaunt2 = pool.checkOut();
    LOGGER.info(checkedOut, oliphaunt2);
    var oliphaunt3 = pool.checkOut();
    LOGGER.info(checkedOut, oliphaunt3);
    LOGGER.info(pool.toString());
    LOGGER.info("Checking in {}", oliphaunt1);
    pool.checkIn(oliphaunt1);
    LOGGER.info("Checking in {}", oliphaunt2);
    pool.checkIn(oliphaunt2);
    LOGGER.info(pool.toString());
    var oliphaunt4 = pool.checkOut();
    LOGGER.info(checkedOut, oliphaunt4);
    var oliphaunt5 = pool.checkOut();
    LOGGER.info(checkedOut, oliphaunt5);
    LOGGER.info(pool.toString());
  }
}
