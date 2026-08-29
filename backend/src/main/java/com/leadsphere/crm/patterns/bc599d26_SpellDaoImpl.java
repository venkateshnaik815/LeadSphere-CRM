package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.common.DaoBaseImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class SpellDaoImpl extends DaoBaseImpl<Spell> implements SpellDao {

  @Override
  public Spell findByName(String name) {
    Transaction tx = null;
    Spell result;
    try (var session = getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
      CriteriaQuery<Spell> builderQuery = criteriaBuilder.createQuery(Spell.class);
      Root<Spell> root = builderQuery.from(Spell.class);
      builderQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name));
      Query<Spell> query = session.createQuery(builderQuery);
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
