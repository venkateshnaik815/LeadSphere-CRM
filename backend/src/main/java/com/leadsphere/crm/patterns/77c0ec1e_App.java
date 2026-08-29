package com.leadsphere.crm.patterns;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class App {

  public static void main(String[] args) {

    var context = new ClassPathXmlApplicationContext("applicationContext.xml");
    var repository = context.getBean(PersonRepository.class);

    var peter = new Person("Peter", "Sagan", 17);
    var nasta = new Person("Nasta", "Kuzminova", 25);
    var john = new Person("John", "lawrence", 35);
    var terry = new Person("Terry", "Law", 36);

    // Add new Person records
    repository.save(peter);
    repository.save(nasta);
    repository.save(john);
    repository.save(terry);

    // Count Person records
    LOGGER.info("Count Person records: {}", repository.count());

    // Print all records
    var persons = (List<Person>) repository.findAll();
    persons.stream().map(Person::toString).forEach(LOGGER::info);

    // Update Person
    nasta.setName("Barbora");
    nasta.setSurname("Spotakova");
    repository.save(nasta);

    repository.findById(2L).ifPresent(p -> LOGGER.info("Find by id 2: {}", p));

    // Remove record from Person
    repository.deleteById(2L);

    // count records
    LOGGER.info("Count Person records: {}", repository.count());

    // find by name
    repository
        .findOne(new PersonSpecifications.NameEqualSpec("John"))
        .ifPresent(p -> LOGGER.info("Find by John is {}", p));

    // find by age
    persons = repository.findAll(new PersonSpecifications.AgeBetweenSpec(20, 40));

    LOGGER.info("Find Person with age between 20,40: ");
    persons.stream().map(Person::toString).forEach(LOGGER::info);

    repository.deleteAll();

    context.close();
  }
}
