package com.leadsphere.crm.patterns;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class HealthCheckRepository {

  private static final String HEALTH_CHECK_OK = "OK";

  @PersistenceContext private EntityManager entityManager;

  public Integer checkHealth() {
    try {
      return (Integer) entityManager.createNativeQuery("SELECT 1").getSingleResult();
    } catch (Exception e) {
      LOGGER.error("Health check query failed", e);
      throw e;
    }
  }

  @Transactional
  public void performTestTransaction() throws Exception {
    try {
      HealthCheck healthCheck = new HealthCheck();
      healthCheck.setStatus(HEALTH_CHECK_OK);
      entityManager.persist(healthCheck);
      entityManager.flush();
      HealthCheck retrievedHealthCheck = entityManager.find(HealthCheck.class, healthCheck.getId());
      entityManager.remove(retrievedHealthCheck);
    } catch (Exception e) {
      LOGGER.error("Test transaction failed", e);
      throw e;
    }
  }
}
