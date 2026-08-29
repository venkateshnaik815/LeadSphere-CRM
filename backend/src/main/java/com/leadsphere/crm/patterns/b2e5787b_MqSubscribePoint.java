package com.leadsphere.crm.patterns;

public interface MqSubscribePoint {

  Message take() throws InterruptedException;
}
