package com.leadsphere.crm.patterns;

import java.util.List;

public final class ImmutableUser {

  private final String name;
  private final int age;
  private final List<String> roles;

  public ImmutableUser(String name, int age, List<String> roles) {
    this.name = name;
    this.age = age;
    this.roles = List.copyOf(roles);
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public List<String> getRoles() {
    return roles;
  }

  public ImmutableUser withName(String newName) {
    return new ImmutableUser(newName, this.age, this.roles);
  }

  public ImmutableUser withAge(int newAge) {
    return new ImmutableUser(this.name, newAge, this.roles);
  }

  public ImmutableUser withRoles(List<String> newRoles) {
    return new ImmutableUser(this.name, this.age, newRoles);
  }

  @Override
  public String toString() {
    return "ImmutableUser{name='" + name + "', age=" + age + ", roles=" + roles + '}';
  }
}
