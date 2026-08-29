package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.spell.Spell;
import com.iluwatar.servicelayer.spellbook.Spellbook;
import com.iluwatar.servicelayer.wizard.Wizard;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public final class HibernateUtil {

  private static volatile SessionFactory sessionFactory;

  private HibernateUtil() {}

  public static synchronized SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        sessionFactory =
            new Configuration()
                .addAnnotatedClass(Wizard.class)
                .addAnnotatedClass(Spellbook.class)
                .addAnnotatedClass(Spell.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
                .setProperty("hibernate.current_session_context_class", "thread")
                .setProperty("hibernate.show_sql", "false")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                .buildSessionFactory();
      } catch (Throwable ex) {
        LOGGER.error("Initial SessionFactory creation failed.", ex);
        throw new ExceptionInInitializerError(ex);
      }
    }
    return sessionFactory;
  }

  public static void dropSession() {
    getSessionFactory().close();
    sessionFactory = null;
  }
}
