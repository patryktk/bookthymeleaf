package pl.tkaczyk.bookthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.tkaczyk.bookthymeleaf.model.Book;
import pl.tkaczyk.bookthymeleaf.model.Movie;
import pl.tkaczyk.bookthymeleaf.service.*;

import java.util.Optional;

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

    @GetMapping("/showMovies")
    public String showMovies(Model model) {
        model.addAttribute("movie", movieService.showMovies());
        return "showMovies";

    }
    @GetMapping("/showBooks")
    public String showBooks(Model model) {
        model.addAttribute("book", bookService.getAllBooks());
        return "showBooks";

    }

    @GetMapping("/addMovieForm")
    public String addMovieForm(Model model){
        model.addAttribute("newMovie",new Movie());
        return "add-Movie";
    }

    @PostMapping("/addMovie")
    public String addMovie(@ModelAttribute Movie movie, BindingResult result){
        if (result.hasErrors()) {
            return "add-Movie";
        }
        movieService.addMovie(movie.getMovieName(), movie.getMovieGenre());
        return "redirect:/";
    }

    @GetMapping("/addBookForm")
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

    @GetMapping("/deleteMovie/{movieName}")
    public String deleteMovie(@PathVariable(value = "movieName") String movieName, Model model){
        movieService.deleteMovie(movieName);
        return "redirect:/";
    }

    @GetMapping("/deleteBook/{book_id}")
    public String deleteBook(@PathVariable(value = "book_id") Long id, Model model){
        Book book = bookRepo.findById(id).orElseThrow(() -> new RuntimeException());
        bookRepo.delete(book);
        return "redirect:/";
    }

    @GetMapping("/editBookForm/{book_id}")
    public String editBookForm(@PathVariable(value="book_id") Long id, Model model){
        Optional<Book> book = bookRepo.findById(id);
        model.addAttribute("book", book);
        return "edit-book";
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
