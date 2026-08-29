package com.leadsphere.crm.patterns;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerDao {

  Optional<Customer> findByName(String name) throws SQLException;

  void update(Customer customer) throws SQLException;

  void save(Customer customer) throws SQLException;

  void addProduct(Product product, Customer customer) throws SQLException;

  void deleteProduct(Product product, Customer customer) throws SQLException;
}
