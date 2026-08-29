package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class PersonFinder {
  private static final long serialVersionUID = 1L;
  //  Access to the Identity Map
  private IdentityMap identityMap = new IdentityMap();
  private PersonDbSimulatorImplementation db = new PersonDbSimulatorImplementation();

  public Person getPerson(int key) {
    // Try to find person in the identity map
    Person person = this.identityMap.getPerson(key);
    if (person != null) {
      LOGGER.info("Person found in the Map");
      return person;
    } else {
      // Try to find person in the database
      person = this.db.find(key);
      if (person != null) {
        this.identityMap.addPerson(person);
        LOGGER.info("Person found in DB.");
        return person;
      }
      LOGGER.info("Person with this ID does not exist.");
      return null;
    }
  }
}
