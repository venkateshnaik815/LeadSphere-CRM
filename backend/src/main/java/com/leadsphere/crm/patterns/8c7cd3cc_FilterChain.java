package com.leadsphere.crm.patterns;

public class FilterChain {

  private Filter chain;

  public void addFilter(Filter filter) {
    if (chain == null) {
      chain = filter;
    } else {
      chain.getLast().setNext(filter);
    }
  }

  public String execute(Order order) {
    if (chain != null) {
      return chain.execute(order);
    } else {
      return "RUNNING...";
    }
  }
}
