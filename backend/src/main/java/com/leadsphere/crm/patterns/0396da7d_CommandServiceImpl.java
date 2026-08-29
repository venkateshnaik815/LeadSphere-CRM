package com.leadsphere.crm.patterns;

import com.iluwatar.cqrs.domain.model.Author;
import com.iluwatar.cqrs.domain.model.Book;
import com.iluwatar.cqrs.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class CommandServiceImpl implements CommandService {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  private Author getAuthorByUsername(String username) {
    Author author;
    try (var session = sessionFactory.openSession()) {
      var query = session.createQuery("from Author where username=:username");
      query.setParameter("username", username);
      author = (Author) query.uniqueResult();
    }
    if (author == null) {
      HibernateUtil.getSessionFactory().close();
      throw new NullPointerException("Author " + username + " doesn't exist!");
    }
    return author;
  }

  private Book getBookByTitle(String title) {
    Book book;
    try (var session = sessionFactory.openSession()) {
      var query = session.createQuery("from Book where title=:title");
      query.setParameter("title", title);
      book = (Book) query.uniqueResult();
    }
    if (book == null) {
      HibernateUtil.getSessionFactory().close();
      throw new NullPointerException("Book " + title + " doesn't exist!");
    }
    return book;
  }

  @Override
  public void authorCreated(String username, String name, String email) {
    var author = new Author(username, name, email);
    try (var session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.save(author);
      session.getTransaction().commit();
    }
  }

  @Override
  public void bookAddedToAuthor(String title, double price, String username) {
    var author = getAuthorByUsername(username);
    var book = new Book(title, price, author);
    try (var session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.save(book);
      session.getTransaction().commit();
    }
  }

  @Override
  public void authorNameUpdated(String username, String name) {
    var author = getAuthorByUsername(username);
    author.setName(name);
    try (var session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.update(author);
      session.getTransaction().commit();
    }
  }

  @Override
  public void authorUsernameUpdated(String oldUsername, String newUsername) {
    var author = getAuthorByUsername(oldUsername);
    author.setUsername(newUsername);
    try (var session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.update(author);
      session.getTransaction().commit();
    }
  }

  @Override
  public void authorEmailUpdated(String username, String email) {
    var author = getAuthorByUsername(username);
    author.setEmail(email);
    try (var session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.update(author);
      session.getTransaction().commit();
    }
  }

  @Override
  public void bookTitleUpdated(String oldTitle, String newTitle) {
    var book = getBookByTitle(oldTitle);
    book.setTitle(newTitle);
    try (var session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.update(book);
      session.getTransaction().commit();
    }
  }

  @Override
  public void bookPriceUpdated(String title, double price) {
    var book = getBookByTitle(title);
    book.setPrice(price);
    try (var session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.update(book);
      session.getTransaction().commit();
    }
  }
}
