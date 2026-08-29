package com.leadsphere.crm.patterns;

import com.iluwatar.onion.domain.exception.DomainException;

public class Person {

  private final Long id;
  private final String firstName;
  private final String lastName;
  private final int age;
  private final String phoneNumber;
  private final String email;
  private final Category category;

  public Person(
      Long id,
      String firstName,
      String lastName,
      int age,
      String phoneNumber,
      String email,
      Category category) {
    validateNames(firstName, lastName);
    validateAge(age);
    validatePhone(phoneNumber);
    validateEmail(email);
    validateCategory(category);

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.category = category;
  }

  private void validateNames(String firstName, String lastName) {
    if (firstName == null || lastName == null) {
      throw new DomainException("First name and last name cannot be null.");
    }
  }

  private void validateAge(int age) {
    if (age < 18) {
      throw new DomainException("Age cannot be less than 18.");
    }
  }

  private void validatePhone(String phone) {
    if (phone == null || phone.isEmpty()) {
      throw new DomainException("Phone number cannot be null or empty.");
    }
  }

  private void validateEmail(String email) {
    if (email == null || email.isEmpty()) {
      throw new DomainException("Email cannot be null or empty.");
    }
  }

  private void validateCategory(Category category) {
    if (category == null || category.getType().isEmpty()) {
      throw new DomainException("Category cannot be null or empty.");
    }
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public Category getCategory() {
    return category;
  }
}
