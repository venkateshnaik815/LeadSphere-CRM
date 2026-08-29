package com.leadsphere.crm.patterns;

import java.util.List;
import lombok.Getter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

public class BookViewModel {

  @WireVariable private List<Book> bookList;
  @Getter private Book selectedBook;
  private BookService bookService = new BookServiceImpl();

  @NotifyChange("selectedBook")
  public void setSelectedBook(Book selectedBook) {
    this.selectedBook = selectedBook;
  }

  public List<Book> getBookList() {
    return bookService.load();
  }

  @Command
  @NotifyChange({"selectedBook", "bookList"})
  public void deleteBook() {
    if (selectedBook != null) {
      getBookList().remove(selectedBook);
      selectedBook = null;
    }
  }
}
