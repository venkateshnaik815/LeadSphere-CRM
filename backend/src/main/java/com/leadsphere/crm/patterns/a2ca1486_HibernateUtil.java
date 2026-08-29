package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@Slf4j
public class HibernateUtil {

  private static final SessionFactory SESSIONFACTORY = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {

    // configures settings from hibernate.cfg.xml
    final var registry = new StandardServiceRegistryBuilder().configure().build();
    try {
      return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception ex) {
      StandardServiceRegistryBuilder.destroy(registry);
      LOGGER.error("Initial SessionFactory creation failed.", ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return SESSIONFACTORY;
  }
}
