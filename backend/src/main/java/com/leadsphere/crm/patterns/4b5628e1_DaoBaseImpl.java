package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.hibernate.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public abstract class DaoBaseImpl<E extends BaseEntity> implements Dao<E> {

  @SuppressWarnings("unchecked")
  protected Class<E> persistentClass =
      (Class<E>)
          ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

  protected SessionFactory getSessionFactory() {
    return HibernateUtil.getSessionFactory();
  }

  @Override
  public E find(Long id) {
    Transaction tx = null;
    E result;
    try (var session = getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
      CriteriaQuery<E> builderQuery = criteriaBuilder.createQuery(persistentClass);
      Root<E> root = builderQuery.from(persistentClass);
      builderQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
      Query<E> query = session.createQuery(builderQuery);
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

  @Override
  public void persist(E entity) {
    Transaction tx = null;
    try (var session = getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      session.persist(entity);
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw e;
    }
  }

  @Override
  public E merge(E entity) {
    Transaction tx = null;
    E result;
    try (var session = getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      result = (E) session.merge(entity);
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw e;
    }
    return result;
  }

  @Override
  public void delete(E entity) {
    Transaction tx = null;
    try (var session = getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      session.delete(entity);
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw e;
    }
  }

  @Override
  public List<E> findAll() {
    Transaction tx = null;
    List<E> result;
    try (var session = getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
      CriteriaQuery<E> builderQuery = criteriaBuilder.createQuery(persistentClass);
      Root<E> root = builderQuery.from(persistentClass);
      builderQuery.select(root);
      Query<E> query = session.createQuery(builderQuery);
      result = query.getResultList();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw e;
    }
    return result;
  }
}
