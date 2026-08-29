package com.leadsphere.crm.patterns;

import com.iluwatar.metamapping.model.User;
import com.iluwatar.metamapping.service.UserService;
import com.iluwatar.metamapping.utils.DatabaseUtil;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.ServiceRegistry;

@Slf4j
public class App {
  public static void main(String[] args) {
    // get service
    var userService = new UserService();
    // use create service to add users
    for (var user : generateSampleUsers()) {
      var id = userService.createUser(user);
      LOGGER.info("Add user" + user + "at" + id + ".");
    }
    // use list service to get users
    var users = userService.listUser();
    LOGGER.info(String.valueOf(users));
    // use get service to get a user
    var user = userService.getUser(1);
    LOGGER.info(String.valueOf(user));
    // change password of user 1
    user.setPassword("new123");
    // use update service to update user 1
    userService.updateUser(1, user);
    // use delete service to delete user 2
    userService.deleteUser(2);
    // close service
    userService.close();
  }

  public static List<User> generateSampleUsers() {
    final var user1 = new User("ZhangSan", "zhs123");
    final var user2 = new User("LiSi", "ls123");
    final var user3 = new User("WangWu", "ww123");
    return List.of(user1, user2, user3);
  }
}
