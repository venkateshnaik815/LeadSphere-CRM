package com.leadsphere.crm.patterns;

@FunctionalInterface
public interface BusinessOperation<T> {
  T perform() throws BusinessException;
}
