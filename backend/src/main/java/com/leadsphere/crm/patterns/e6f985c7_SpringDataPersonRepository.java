package com.leadsphere.crm.patterns;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPersonRepository extends JpaRepository<JpaPersonEntity, Long> {

  Optional<JpaPersonEntity> findByFirstName(String firstName);

  Optional<JpaPersonEntity> findByLastName(String lastName);
}
