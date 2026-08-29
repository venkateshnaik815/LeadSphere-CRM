package com.leadsphere.crm.patterns;

public class UserConverter extends Converter<UserDto, User> {

  public UserConverter() {
    super(UserConverter::convertToEntity, UserConverter::convertToDto);
  }

  private static UserDto convertToDto(User user) {
    return new UserDto(user.firstName(), user.lastName(), user.active(), user.userId());
  }

  private static User convertToEntity(UserDto dto) {
    return new User(dto.firstName(), dto.lastName(), dto.active(), dto.email());
  }
}
