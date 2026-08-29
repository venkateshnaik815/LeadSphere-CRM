package com.leadsphere.crm.patterns;

public interface Filter {

  String execute(Order order);

  void setNext(Filter filter);

  Filter getNext();

  Filter getLast();
}
