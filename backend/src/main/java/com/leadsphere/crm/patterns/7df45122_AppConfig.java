package com.leadsphere.crm.patterns;

import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@EnableJpaRepositories
@SpringBootConfiguration
@Slf4j
public class AppConfig {

  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    var basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("org.h2.Driver");
    basicDataSource.setUrl("jdbc:h2:mem:databases-person");
    basicDataSource.setUsername("sa");
    basicDataSource.setPassword("sa");
    return basicDataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    var entityManager = new LocalContainerEntityManagerFactoryBean();
    entityManager.setDataSource(dataSource());
    entityManager.setPackagesToScan("com.iluwatar");
    entityManager.setPersistenceProvider(new HibernatePersistenceProvider());
    entityManager.setJpaProperties(jpaProperties());
    return entityManager;
  }

  private static Properties jpaProperties() {
    var properties = new Properties();
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    return properties;
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    var transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }

  public static void main(String[] args) {
    var context = new AnnotationConfigApplicationContext(AppConfig.class);
    var repository = context.getBean(PersonRepository.class);

    var peter = new Person("Peter", "Sagan", 17);
    var nasta = new Person("Nasta", "Kuzminova", 25);
    var john = new Person("John", "lawrence", 35);
    var terry = new Person("Terry", "Law", 36);

    // Add new Person records
    repository.save(peter);
    repository.save(nasta);
    repository.save(john);
    repository.save(terry);

    // Count Person records
    LOGGER.info("Count Person records: {}", repository.count());

    // Print all records
    var persons = (List<Person>) repository.findAll();
    persons.stream().map(Person::toString).forEach(LOGGER::info);

    // Update Person
    nasta.setName("Barbora");
    nasta.setSurname("Spotakova");
    repository.save(nasta);

    repository.findById(2L).ifPresent(p -> LOGGER.info("Find by id 2: {}", p));

    // Remove record from Person
    repository.deleteById(2L);

    // count records
    LOGGER.info("Count Person records: {}", repository.count());

    // find by name
    repository
        .findOne(new PersonSpecifications.NameEqualSpec("John"))
        .ifPresent(p -> LOGGER.info("Find by John is {}", p));

    // find by age
    persons = repository.findAll(new PersonSpecifications.AgeBetweenSpec(20, 40));

    LOGGER.info("Find Person with age between 20,40: ");
    persons.stream().map(Person::toString).forEach(LOGGER::info);

    context.close();
  }
}
