package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) {
    var filterManager = new FilterManager();
    filterManager.addFilter(new NameFilter());
    filterManager.addFilter(new ContactFilter());
    filterManager.addFilter(new AddressFilter());
    filterManager.addFilter(new DepositFilter());
    filterManager.addFilter(new OrderFilter());

    var client = new Client();
    client.setFilterManager(filterManager);
  }
}
