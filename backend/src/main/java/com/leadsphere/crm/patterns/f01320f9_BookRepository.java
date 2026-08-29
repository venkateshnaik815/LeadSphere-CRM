package com.leadsphere.crm.patterns;

import java.util.concurrent.ConcurrentHashMap;

public class BookRepository {
  private final ConcurrentHashMap<Long, Book> collection = new ConcurrentHashMap<>();
  private final Object lock = new Object();

  public void add(Book book) throws BookDuplicateException {
    if (collection.containsKey(book.getId())) {
      throw new BookDuplicateException("Duplicated book with id: " + book.getId());
    }

    // add copy of the book
    collection.put(book.getId(), new Book(book));
  }

  public void update(Book book) throws BookNotFoundException, VersionMismatchException {
    if (!collection.containsKey(book.getId())) {
      throw new BookNotFoundException("Not found book with id: " + book.getId());
    }

    // used synchronized block to ensure only one thread compares and update the version
    synchronized (lock) {
      var latestBook = collection.get(book.getId());
      if (book.getVersion() != latestBook.getVersion()) {
        throw new VersionMismatchException(
            "Tried to update stale version "
                + book.getVersion()
                + " while actual version is "
                + latestBook.getVersion());
      }

      // update version, including client representation - modify by reference here
      book.setVersion(book.getVersion() + 1);

      // save book copy to repository
      collection.put(book.getId(), new Book(book));
    }
  }

  public Book get(long bookId) throws BookNotFoundException {
    if (!collection.containsKey(bookId)) {
      throw new BookNotFoundException("Not found book with id: " + bookId);
    }

    // return copy of the book
    return new Book(collection.get(bookId));
  }
}
