package com.leadsphere.crm.patterns;

import com.iluwatar.monolithic.model.User;
import com.iluwatar.monolithic.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User registerUser(User user) {
    return userRepository.save(user);
  }
}
