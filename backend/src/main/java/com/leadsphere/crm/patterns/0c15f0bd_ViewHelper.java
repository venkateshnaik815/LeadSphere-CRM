package com.leadsphere.crm.patterns;

public interface ViewHelper<M, V> {
  V prepare(M source);
}
