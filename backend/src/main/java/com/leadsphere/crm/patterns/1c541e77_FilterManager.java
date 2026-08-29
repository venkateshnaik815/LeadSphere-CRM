package com.leadsphere.crm.patterns;

public class FilterManager {

  private final FilterChain filterChain;

  public FilterManager() {
    filterChain = new FilterChain();
  }

  public void addFilter(Filter filter) {
    filterChain.addFilter(filter);
  }

  public String filterRequest(Order order) {
    return filterChain.execute(order);
  }
}
