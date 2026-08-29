package com.leadsphere.crm.patterns;

public interface UpdateService<T> {

  T doUpdate(T obj, long id);
}
