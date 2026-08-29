package com.leadsphere.crm.patterns;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    Converter<UserDto, User> userConverter = new UserConverter();

    UserDto dtoUser = new UserDto("John", "Doe", true, "whatever[at]wherever.com");
    User user = userConverter.convertFromDto(dtoUser);
    LOGGER.info("Entity converted from DTO: {}", user);

    var users =
        List.of(
            new User("Camile", "Tough", false, "124sad"),
            new User("Marti", "Luther", true, "42309fd"),
            new User("Kate", "Smith", true, "if0243"));
    LOGGER.info("Domain entities:");
    users.stream().map(User::toString).forEach(LOGGER::info);

    LOGGER.info("DTO entities converted from domain:");
    List<UserDto> dtoEntities = userConverter.createFromEntities(users);
    dtoEntities.stream().map(UserDto::toString).forEach(LOGGER::info);
  }
}
