package com.leadsphere.crm.patterns;

public interface MqPublishPoint {

  void put(Message msg) throws InterruptedException;
}
