package com.leadsphere.crm.patterns;

import com.iluwatar.metamapping.model.User;
import com.iluwatar.metamapping.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

@Slf4j
public class UserService {
  private static final SessionFactory factory = HibernateUtil.getSessionFactory();

  public List<User> listUser() {
    LOGGER.info("list all users.");
    List<User> users = new ArrayList<>();
    try (var session = factory.openSession()) {
      var tx = session.beginTransaction();
      List<User> userIter = session.createQuery("FROM User").list();
      for (User user : userIter) {
        users.add(user);
      }
      tx.commit();
    } catch (HibernateException e) {
      LOGGER.debug("fail to get users", e);
    }
    return users;
  }

  public int createUser(User user) {
    LOGGER.info("create user: " + user.getUsername());
    var id = -1;
    try (var session = factory.openSession()) {
      var tx = session.beginTransaction();
      id = (Integer) session.save(user);
      tx.commit();
    } catch (HibernateException e) {
      LOGGER.debug("fail to create user", e);
    }
    LOGGER.info("create user " + user.getUsername() + " at " + id);
    return id;
  }

  public void updateUser(Integer id, User user) {
    LOGGER.info("update user at " + id);
    try (var session = factory.openSession()) {
      var tx = session.beginTransaction();
      user.setId(id);
      session.update(user);
      tx.commit();
    } catch (HibernateException e) {
      LOGGER.debug("fail to update user", e);
    }
  }

  public void deleteUser(Integer id) {
    LOGGER.info("delete user at: " + id);
    try (var session = factory.openSession()) {
      var tx = session.beginTransaction();
      var user = session.get(User.class, id);
      session.delete(user);
      tx.commit();
    } catch (HibernateException e) {
      LOGGER.debug("fail to delete user", e);
    }
  }

  public User getUser(Integer id) {
    LOGGER.info("get user at: " + id);
    User user = null;
    try (var session = factory.openSession()) {
      var tx = session.beginTransaction();
      user = session.get(User.class, id);
      tx.commit();
    } catch (HibernateException e) {
      LOGGER.debug("fail to get user", e);
    }
    return user;
  }

  public void close() {
    HibernateUtil.shutdown();
  }
}
