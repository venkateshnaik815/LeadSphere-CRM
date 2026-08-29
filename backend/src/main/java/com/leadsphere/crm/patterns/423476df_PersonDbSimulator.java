package com.leadsphere.crm.patterns;

public interface PersonDbSimulator {
  Person find(int personNationalId);

  void insert(Person person);

  void update(Person person);

  void delete(int personNationalId);
}
