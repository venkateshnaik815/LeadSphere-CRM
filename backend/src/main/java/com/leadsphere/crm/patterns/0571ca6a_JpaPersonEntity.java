package com.leadsphere.crm.patterns;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class JpaPersonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private int age;
  private String phoneNumber;
  private String email;

  @ManyToOne private JpaCategoryEntity category;

  public JpaPersonEntity() {}

  public JpaPersonEntity(
      Long id,
      String firstName,
      String lastName,
      int age,
      String phoneNumber,
      String email,
      JpaCategoryEntity category) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.category = category;
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

  public JpaCategoryEntity getCategory() {
    return category;
  }
}
