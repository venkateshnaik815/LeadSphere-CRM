package com.leadsphere.crm.patterns;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    var user = new User("user", 24, Sex.FEMALE, "foobar.com");
    LOGGER.info(
        Validator.of(user)
            .validate(User::name, Objects::nonNull, "name is null")
            .validate(User::name, name -> !name.isEmpty(), "name is empty")
            .validate(User::email, email -> !email.contains("@"), "email doesn't contains '@'")
            .validate(User::age, age -> age > 20 && age < 30, "age isn't between...")
            .get()
            .toString());
  }
}
