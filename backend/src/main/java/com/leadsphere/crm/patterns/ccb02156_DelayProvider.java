package com.leadsphere.crm.patterns;

import java.util.concurrent.TimeUnit;

public interface DelayProvider {
  void executeAfterDelay(long interval, TimeUnit timeUnit, Runnable task);
}
