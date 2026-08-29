package com.leadsphere.crm.patterns;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CustomerDAO<T extends Serializable> {
  void save(Customer<T> customer);

  void update(Customer<T> customer);

  void delete(T id);

  List<Customer<T>> findAll();

  Optional<Customer<T>> findById(T id);

  void deleteSchema();
}
