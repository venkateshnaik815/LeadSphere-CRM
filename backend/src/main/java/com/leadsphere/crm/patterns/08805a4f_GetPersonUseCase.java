package com.leadsphere.crm.patterns;

import com.iluwatar.onion.application.dto.PersonResponse;
import com.iluwatar.onion.domain.repository.PersonRepository;
import java.util.Collection;
import java.util.List;

public class GetPersonUseCase {

  private final PersonRepository repository;

  public GetPersonUseCase(PersonRepository repository) {
    this.repository = repository;
  }

  public PersonResponse execute(Long id) {
    var person =
        repository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));

    return new PersonResponse(
        person.getId(),
        person.getFirstName(),
        person.getLastName(),
        person.getAge(),
        person.getPhoneNumber(),
        person.getEmail(),
        person.getCategory().getId(),
        person.getCategory().getType());
  }

  public List<PersonResponse> executeAll() {
    return repository.findAll().stream()
        .flatMap(Collection::stream)
        .map(
            person ->
                new PersonResponse(
                    person.getId(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getAge(),
                    person.getPhoneNumber(),
                    person.getEmail(),
                    person.getCategory().getId(),
                    person.getCategory().getType()))
        .toList();
  }
}
