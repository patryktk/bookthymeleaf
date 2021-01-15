package pl.tkaczyk.bookthymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tkaczyk.bookthymeleaf.model.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private BookRepo bookRepo;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public boolean findBookByTitle(String title) {
        bookRepo.findBookByTytul(title);
        return true;
    }


}