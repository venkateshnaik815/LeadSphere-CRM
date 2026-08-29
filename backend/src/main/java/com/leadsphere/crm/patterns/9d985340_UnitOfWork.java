package com.leadsphere.crm.patterns;

public interface UnitOfWork<T> {

  void registerNew(T entity);

  void registerModified(T entity);

  void registerDeleted(T entity);

  void commit();
}
