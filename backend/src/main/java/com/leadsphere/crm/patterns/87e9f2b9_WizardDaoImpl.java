package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.common.DaoBaseImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class WizardDaoImpl extends DaoBaseImpl<Wizard> implements WizardDao {

  @Override
  public Wizard findByName(String name) {
    Transaction tx = null;
    Wizard result;
    try (var session = getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
      CriteriaQuery<Wizard> builderQuery = criteriaBuilder.createQuery(Wizard.class);
      Root<Wizard> root = builderQuery.from(Wizard.class);
      builderQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name));
      Query<Wizard> query = session.createQuery(builderQuery);
      result = query.uniqueResult();
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw e;
    }
    return result;
  }
}
