package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class IdentityMap {
  private Map<Integer, Person> personMap = new HashMap<>();

  public void addPerson(Person person) {
    if (!personMap.containsKey(person.getPersonNationalId())) {
      personMap.put(person.getPersonNationalId(), person);
    } else { // Ensure that addPerson does not update a record. This situation will never arise in
      // our implementation. Added only for testing purposes.
      LOGGER.info("Key already in Map");
    }
  }

  public Person getPerson(int id) {
    Person person = personMap.get(id);
    if (person == null) {
      LOGGER.info("ID not in Map.");
      return null;
    }
    LOGGER.info(person.toString());
    return person;
  }

  public int size() {
    if (personMap == null) {
      return 0;
    }
    return personMap.size();
  }
}
