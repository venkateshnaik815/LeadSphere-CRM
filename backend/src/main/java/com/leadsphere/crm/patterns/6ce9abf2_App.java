package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    // eagerly initialized singleton
    var ivoryTower1 = IvoryTower.getInstance();
    var ivoryTower2 = IvoryTower.getInstance();
    LOGGER.info("ivoryTower1={}", ivoryTower1);
    LOGGER.info("ivoryTower2={}", ivoryTower2);

    // lazily initialized singleton
    var threadSafeIvoryTower1 = ThreadSafeLazyLoadedIvoryTower.getInstance();
    var threadSafeIvoryTower2 = ThreadSafeLazyLoadedIvoryTower.getInstance();
    LOGGER.info("threadSafeIvoryTower1={}", threadSafeIvoryTower1);
    LOGGER.info("threadSafeIvoryTower2={}", threadSafeIvoryTower2);

    // enum singleton
    var enumIvoryTower1 = EnumIvoryTower.INSTANCE;
    var enumIvoryTower2 = EnumIvoryTower.INSTANCE;
    LOGGER.info("enumIvoryTower1={}", enumIvoryTower1);
    LOGGER.info("enumIvoryTower2={}", enumIvoryTower2);

    // double-checked locking
    var dcl1 = ThreadSafeDoubleCheckLocking.getInstance();
    LOGGER.info(dcl1.toString());
    var dcl2 = ThreadSafeDoubleCheckLocking.getInstance();
    LOGGER.info(dcl2.toString());

    // initialize on demand holder idiom
    var demandHolderIdiom = InitializingOnDemandHolderIdiom.getInstance();
    LOGGER.info(demandHolderIdiom.toString());
    var demandHolderIdiom2 = InitializingOnDemandHolderIdiom.getInstance();
    LOGGER.info(demandHolderIdiom2.toString());

    // initialize singleton using Bill Pugh's implementation
    var billPughSingleton = BillPughImplementation.getInstance();
    LOGGER.info(billPughSingleton.toString());
    var billPughSingleton2 = BillPughImplementation.getInstance();
    LOGGER.info(billPughSingleton2.toString());
  }
}
