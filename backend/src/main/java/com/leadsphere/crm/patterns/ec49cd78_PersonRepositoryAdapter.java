package com.leadsphere.crm.patterns;

import com.iluwatar.onion.domain.model.Category;
import com.iluwatar.onion.domain.model.Person;
import com.iluwatar.onion.domain.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryAdapter implements PersonRepository {

  private final SpringDataPersonRepository repository;

  public PersonRepositoryAdapter(SpringDataPersonRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Person> findById(Long id) {
    return repository.findById(id).map(this::mapToDomain);
  }

  @Override
  public Optional<Person> findByFirstName(String firstName) {
    return repository.findByFirstName(firstName).map(this::mapToDomain);
  }

  @Override
  public Optional<Person> findByLastName(String lastName) {
    return repository.findByLastName(lastName).map(this::mapToDomain);
  }

  @Override
  public Optional<List<Person>> findAll() {
    return repository.findAll().stream()
        .map(this::mapToDomain)
        .collect(Collectors.collectingAndThen(Collectors.toList(), Optional::of));
  }

  @Override
  public Person save(Person person) {
    JpaPersonEntity entity = mapToEntity(person);
    JpaPersonEntity savedEntity = repository.save(entity);
    return mapToDomain(savedEntity);
  }

  @Override
  public boolean deleteById(Long id) {
    repository.deleteById(id);
    return repository.findById(id).isEmpty();
  }

  private JpaPersonEntity mapToEntity(Person person) {
    return new JpaPersonEntity(
        person.getId(),
        person.getFirstName(),
        person.getLastName(),
        person.getAge(),
        person.getPhoneNumber(),
        person.getEmail(),
        new JpaCategoryEntity(person.getCategory().getId(), person.getCategory().getType()));
  }

  private Person mapToDomain(JpaPersonEntity entity) {
    return new Person(
        entity.getId(),
        entity.getFirstName(),
        entity.getLastName(),
        entity.getAge(),
        entity.getPhoneNumber(),
        entity.getEmail(),
        new Category(entity.getCategory().getId(), entity.getCategory().getType()));
  }
}
