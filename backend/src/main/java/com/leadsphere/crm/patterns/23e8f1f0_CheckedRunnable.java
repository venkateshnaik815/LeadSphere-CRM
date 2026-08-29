package com.leadsphere.crm.patterns;

@FunctionalInterface
public interface CheckedRunnable {
  void run() throws Exception;
}
