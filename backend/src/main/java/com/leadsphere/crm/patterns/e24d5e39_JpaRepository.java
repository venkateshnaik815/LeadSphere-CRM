package com.leadsphere.crm.patterns;

public interface JpaRepository<T> {

  T findById(long id);

  int getEntityVersionById(long id);

  int update(T obj);
}
