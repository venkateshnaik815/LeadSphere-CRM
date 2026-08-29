package com.leadsphere.crm.patterns;

import com.iluwatar.onion.application.dto.PersonResponse;
import com.iluwatar.onion.application.dto.SavePersonCommand;
import com.iluwatar.onion.domain.model.Category;
import com.iluwatar.onion.domain.model.Person;
import com.iluwatar.onion.domain.repository.PersonRepository;

public class SavePersonUseCase {

  private final PersonRepository repository;

  public SavePersonUseCase(PersonRepository repository) {
    this.repository = repository;
  }

  public PersonResponse execute(SavePersonCommand command) {
    var category = new Category(command.categoryId(), command.categoryType());
    var person =
        new Person(
            null,
            command.firstName(),
            command.lastName(),
            command.age(),
            command.phoneNumber(),
            command.email(),
            category);

    var savedPerson = repository.save(person);

    return new PersonResponse(
        savedPerson.getId(),
        savedPerson.getFirstName(),
        savedPerson.getLastName(),
        savedPerson.getAge(),
        savedPerson.getPhoneNumber(),
        savedPerson.getEmail(),
        savedPerson.getCategory().getId(),
        savedPerson.getCategory().getType());
  }
}
