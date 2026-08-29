package com.leadsphere.crm.patterns;

public interface Rule<T> {

  String name();

  boolean evaluate(T context);

  void execute(T context);
}
