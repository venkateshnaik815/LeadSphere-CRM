package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class HibernateUtil {

  @Getter private static final SessionFactory sessionFactory = buildSessionFactory();

  private HibernateUtil() {}

  private static SessionFactory buildSessionFactory() {
    // Create the SessionFactory from hibernate.cfg.xml
    return new Configuration().configure().buildSessionFactory();
  }

  public static void shutdown() {
    // Close caches and connection pools
    getSessionFactory().close();
  }
}
