package com.leadsphere.crm.patterns;

import com.iluwatar.cqrs.commandes.CommandServiceImpl;
import com.iluwatar.cqrs.constants.AppConstants;
import com.iluwatar.cqrs.queries.QueryServiceImpl;
import com.iluwatar.cqrs.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    // Create Authors and Books using CommandService
    var commands = new CommandServiceImpl();

    commands.authorCreated(AppConstants.E_EVANS, "Eric Evans", "evans@email.com");
    commands.authorCreated(AppConstants.J_BLOCH, "Joshua Bloch", "jBloch@email.com");
    commands.authorCreated(AppConstants.M_FOWLER, "Martin Fowler", "mFowler@email.com");

    commands.bookAddedToAuthor("Domain-Driven Design", 60.08, AppConstants.E_EVANS);
    commands.bookAddedToAuthor("Effective Java", 40.54, AppConstants.J_BLOCH);
    commands.bookAddedToAuthor("Java Puzzlers", 39.99, AppConstants.J_BLOCH);
    commands.bookAddedToAuthor("Java Concurrency in Practice", 29.40, AppConstants.J_BLOCH);
    commands.bookAddedToAuthor(
        "Patterns of Enterprise" + " Application Architecture", 54.01, AppConstants.M_FOWLER);
    commands.bookAddedToAuthor("Domain Specific Languages", 48.89, AppConstants.M_FOWLER);
    commands.authorNameUpdated(AppConstants.E_EVANS, "Eric J. Evans");

    // Query the database using QueryService
    var queries = new QueryServiceImpl();

    var nullAuthor = queries.getAuthorByUsername("username");
    var evans = queries.getAuthorByUsername(AppConstants.E_EVANS);
    var blochBooksCount = queries.getAuthorBooksCount(AppConstants.J_BLOCH);
    var authorsCount = queries.getAuthorsCount();
    var dddBook = queries.getBook("Domain-Driven Design");
    var blochBooks = queries.getAuthorBooks(AppConstants.J_BLOCH);

    LOGGER.info("Author username : {}", nullAuthor);
    LOGGER.info("Author evans : {}", evans);
    LOGGER.info("jBloch number of books : {}", blochBooksCount);
    LOGGER.info("Number of authors : {}", authorsCount);
    LOGGER.info("DDD book : {}", dddBook);
    LOGGER.info("jBloch books : {}", blochBooks);

    HibernateUtil.getSessionFactory().close();
  }
}
