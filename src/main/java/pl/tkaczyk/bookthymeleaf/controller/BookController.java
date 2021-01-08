package pl.tkaczyk.bookthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.tkaczyk.bookthymeleaf.model.Book;
import pl.tkaczyk.bookthymeleaf.service.BookRepo;
import pl.tkaczyk.bookthymeleaf.service.BookService;
import pl.tkaczyk.bookthymeleaf.service.MovieService;

@Controller
public class BookController {
    BookRepo bookRepo;
    BookService bookService;
    MovieService movieService;

    @Autowired
    public BookController(BookRepo bookRepo, BookService bookService, MovieService movieService) {
        this.bookRepo = bookRepo;
        this.bookService = bookService;
        this.movieService = movieService;
    }
    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("/showBooks")
    public String showBooks(Model model){
        model.addAttribute("book", bookService.getAllBooks());
        return "showBooks";

    }

    @GetMapping("/addBook")
    public String addBooksForm(Book book){
        return "addBooks";
    }

    @PostMapping("/add-Book")
    public String addBook(@Validated Book book, BindingResult result, Model model){
        if (result.hasErrors()){
            return "addBooks";
        }
        bookService.addBook(book);
        return "redirect:/index";
    }

    @GetMapping("/deleteBook")
    public String deleteBookForm(Book book){
        return "deleteBook";
    }

    @DeleteMapping("/delete/{tytul}")
    public String deleteBook(@PathVariable("tytul") String tytul, Model model){
        bookRepo.deleteByTytul(tytul);
        return "redirect:/showBooks";
    }
}
