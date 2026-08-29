package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
  public static void main(String[] args) {

    // Dummy Persons
    Person person1 = new Person(1, "John", 27304159);
    Person person2 = new Person(2, "Thomas", 42273631);
    Person person3 = new Person(3, "Arthur", 27489171);
    Person person4 = new Person(4, "Finn", 20499078);
    Person person5 = new Person(5, "Michael", 40599078);

    // Init database
    PersonDbSimulatorImplementation db = new PersonDbSimulatorImplementation();
    db.insert(person1);
    db.insert(person2);
    db.insert(person3);
    db.insert(person4);
    db.insert(person5);

    // Init a personFinder
    PersonFinder finder = new PersonFinder();
    finder.setDb(db);

    // Find persons in DataBase not the map.
    LOGGER.info(finder.getPerson(2).toString());
    LOGGER.info(finder.getPerson(4).toString());
    LOGGER.info(finder.getPerson(5).toString());
    // Find the person in the map.
    LOGGER.info(finder.getPerson(2).toString());
  }
}
