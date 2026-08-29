package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args)
      throws BookDuplicateException, BookNotFoundException, VersionMismatchException {
    var bookId = 1;

    var bookRepository = new BookRepository();
    var book = new Book();
    book.setId(bookId);
    bookRepository.add(book); // adding a book with empty title and author
    LOGGER.info("An empty book with version {} was added to repository", book.getVersion());

    // Alice and Bob took the book concurrently
    final var aliceBook = bookRepository.get(bookId);
    final var bobBook = bookRepository.get(bookId);

    aliceBook.setTitle("Kama Sutra"); // Alice has updated book title
    bookRepository.update(aliceBook); // and successfully saved book in database
    LOGGER.info("Alice updates the book with new version {}", aliceBook.getVersion());

    // now Bob has the stale version of the book with empty title and version = 0
    // while actual book in database has filled title and version = 1
    bobBook.setAuthor("Vatsyayana Mallanaga"); // Bob updates the author
    try {
      LOGGER.info("Bob tries to update the book with his version {}", bobBook.getVersion());
      bookRepository.update(bobBook); // Bob tries to save his book to database
    } catch (VersionMismatchException e) {
      // Bob update fails, and book in repository remained untouchable
      LOGGER.info("Exception: {}", e.getMessage());
      // Now Bob should reread actual book from repository, do his changes again and save again
    }
  }
}
