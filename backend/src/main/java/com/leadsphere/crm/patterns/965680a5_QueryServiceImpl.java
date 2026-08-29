package com.leadsphere.crm.patterns;

import com.iluwatar.cqrs.constants.AppConstants;
import com.iluwatar.cqrs.dto.Author;
import com.iluwatar.cqrs.dto.Book;
import com.iluwatar.cqrs.util.HibernateUtil;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class QueryServiceImpl implements QueryService {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public Author getAuthorByUsername(String username) {
    Author authorDto;
    try (var session = sessionFactory.openSession()) {
      Query<Author> sqlQuery =
          session.createQuery(
              "select new com.iluwatar.cqrs.dto.Author(a.name, a.email, a.username)"
                  + " from com.iluwatar.cqrs.domain.model.Author a where a.username=:username");
      sqlQuery.setParameter(AppConstants.USER_NAME, username);
      authorDto = sqlQuery.uniqueResult();
    }
    return authorDto;
  }

  @Override
  public Book getBook(String title) {
    Book bookDto;
    try (var session = sessionFactory.openSession()) {
      Query<Book> sqlQuery =
          session.createQuery(
              "select new com.iluwatar.cqrs.dto.Book(b.title, b.price)"
                  + " from com.iluwatar.cqrs.domain.model.Book b where b.title=:title");
      sqlQuery.setParameter("title", title);
      bookDto = sqlQuery.uniqueResult();
    }
    return bookDto;
  }

  @Override
  public List<Book> getAuthorBooks(String username) {
    List<Book> bookDtos;
    try (var session = sessionFactory.openSession()) {
      Query<Book> sqlQuery =
          session.createQuery(
              "select new com.iluwatar.cqrs.dto.Book(b.title, b.price)"
                  + " from com.iluwatar.cqrs.domain.model.Author a, com.iluwatar.cqrs.domain.model.Book b "
                  + "where b.author.id = a.id and a.username=:username");
      sqlQuery.setParameter(AppConstants.USER_NAME, username);
      bookDtos = sqlQuery.list();
    }
    return bookDtos;
  }

  @Override
  public BigInteger getAuthorBooksCount(String username) {
    BigInteger bookcount;
    try (var session = sessionFactory.openSession()) {
      var sqlQuery =
          session.createNativeQuery(
              "SELECT count(b.title)"
                  + " FROM  Book b, Author a"
                  + " where b.author_id = a.id and a.username=:username");
      sqlQuery.setParameter(AppConstants.USER_NAME, username);
      bookcount = (BigInteger) sqlQuery.uniqueResult();
    }
    return bookcount;
  }

  @Override
  public BigInteger getAuthorsCount() {
    BigInteger authorcount;
    try (var session = sessionFactory.openSession()) {
      var sqlQuery = session.createNativeQuery("SELECT count(id) from Author");
      authorcount = (BigInteger) sqlQuery.uniqueResult();
    }
    return authorcount;
  }
}
