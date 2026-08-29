package com.leadsphere.crm.patterns;

import com.iluwatar.monolithic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
}
