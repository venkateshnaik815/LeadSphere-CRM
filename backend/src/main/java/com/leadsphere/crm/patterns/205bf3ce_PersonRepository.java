package com.leadsphere.crm.patterns;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository
    extends CrudRepository<Person, Long>, JpaSpecificationExecutor<Person> {

  Person findByName(String name);

  List<Person> findAll();
}
