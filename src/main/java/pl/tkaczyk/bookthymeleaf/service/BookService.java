package pl.tkaczyk.bookthymeleaf.service;

import pl.tkaczyk.bookthymeleaf.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();


    boolean findBookByTitle(String title);

}
