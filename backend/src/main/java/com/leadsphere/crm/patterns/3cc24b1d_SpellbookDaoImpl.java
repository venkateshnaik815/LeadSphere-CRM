package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.common.DaoBaseImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class SpellbookDaoImpl extends DaoBaseImpl<Spellbook> implements SpellbookDao {

  @Override
  public Spellbook findByName(String name) {
    Transaction tx = null;
    Spellbook result;
    try (var session = getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
      CriteriaQuery<Spellbook> builderQuery = criteriaBuilder.createQuery(Spellbook.class);
      Root<Spellbook> root = builderQuery.from(Spellbook.class);
      builderQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name));
      Query<Spellbook> query = session.createQuery(builderQuery);
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
