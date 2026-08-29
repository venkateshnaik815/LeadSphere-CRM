package com.leadsphere.crm.patterns;

import com.iluwatar.onion.domain.model.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepository {
  Optional<Person> findById(Long id);

  Optional<Person> findByFirstName(String firstName);

  Optional<Person> findByLastName(String lastName);

  Optional<List<Person>> findAll();

  Person save(Person person);

  boolean deleteById(Long id);
}
