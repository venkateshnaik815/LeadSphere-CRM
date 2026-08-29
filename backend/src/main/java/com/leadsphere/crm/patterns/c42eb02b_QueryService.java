package com.leadsphere.crm.patterns;

import com.iluwatar.cqrs.dto.Author;
import com.iluwatar.cqrs.dto.Book;
import java.math.BigInteger;
import java.util.List;

public interface QueryService {

  Author getAuthorByUsername(String username);

  Book getBook(String title);

  List<Book> getAuthorBooks(String username);

  BigInteger getAuthorBooksCount(String username);

  BigInteger getAuthorsCount();
}
