package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonDbSimulatorImplementation implements PersonDbSimulator {

  // This simulates a table in the database. To extend logic to multiple tables just add more lists
  // to the implementation.
  private List<Person> personList = new ArrayList<>();
  static final String NOT_IN_DATA_BASE = " not in DataBase";
  static final String ID_STR = "ID : ";

  @Override
  public Person find(int personNationalId) throws IdNotFoundException {
    Optional<Person> elem =
        personList.stream().filter(p -> p.getPersonNationalId() == personNationalId).findFirst();
    if (elem.isEmpty()) {
      throw new IdNotFoundException(ID_STR + personNationalId + NOT_IN_DATA_BASE);
    }
    LOGGER.info(elem.get().toString());
    return elem.get();
  }

  @Override
  public void insert(Person person) {
    Optional<Person> elem =
        personList.stream()
            .filter(p -> p.getPersonNationalId() == person.getPersonNationalId())
            .findFirst();
    if (elem.isPresent()) {
      LOGGER.info("Record already exists.");
      return;
    }
    personList.add(person);
  }

  @Override
  public void update(Person person) throws IdNotFoundException {
    Optional<Person> elem =
        personList.stream()
            .filter(p -> p.getPersonNationalId() == person.getPersonNationalId())
            .findFirst();
    if (elem.isPresent()) {
      elem.get().setName(person.getName());
      elem.get().setPhoneNum(person.getPhoneNum());
      LOGGER.info("Record updated successfully");
      return;
    }
    throw new IdNotFoundException(ID_STR + person.getPersonNationalId() + NOT_IN_DATA_BASE);
  }

  public void delete(int id) throws IdNotFoundException {
    Optional<Person> elem =
        personList.stream().filter(p -> p.getPersonNationalId() == id).findFirst();
    if (elem.isPresent()) {
      personList.remove(elem.get());
      LOGGER.info("Record deleted successfully.");
      return;
    }
    throw new IdNotFoundException(ID_STR + id + NOT_IN_DATA_BASE);
  }

  public int size() {
    if (personList == null) {
      return 0;
    }
    return personList.size();
  }
}
