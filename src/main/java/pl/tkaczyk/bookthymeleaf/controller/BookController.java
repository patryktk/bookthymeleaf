package pl.tkaczyk.bookthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
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
    public String index() {
        return "index";
    }

    @GetMapping("/showBooks")
    public String showBooks(Model model) {
        model.addAttribute("book", bookService.getAllBooks());
        return "showBooks";

    }

    @GetMapping("/dodaj")
    public String addBookForm(Book book) {
        return "add-Books";
    }

    @PostMapping("/addbook")
    public String addBook(@Validated Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-Books";
        }
        bookRepo.save(book);
        return "redirect:/";
    }

    @GetMapping("/deleteBook/{book_id}")
    public String deleteBook(@PathVariable(value = "book_id") Long id, Model model){
        Book book = bookRepo.findById(id).orElseThrow(() -> new RuntimeException());
        bookRepo.delete(book);
        return "redirect:/";
    }

    @GetMapping("/editBookForm/{book_id]")
    public String editBookForm(@PathVariable(value = "book_id") Long id, Model model){
        Book book = bookRepo.findById(id).orElseThrow(() -> new RuntimeException());

        model.addAttribute("book", book);
        return "edit-Book";
    }

    @PostMapping("/editBook/{book_id}")
    public String editBook(@PathVariable(value = "book_id") Long id, @Validated Book book, BindingResult result, Model model){
        if(result.hasErrors()){
            return "edit-book";
        }
        bookRepo.save(book);
        return "redirect:/";
    }

}
