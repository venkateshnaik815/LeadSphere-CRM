package com.leadsphere.crm.patterns;

public interface ClientBff<T> {

  T getDashboard(String userId);
}
