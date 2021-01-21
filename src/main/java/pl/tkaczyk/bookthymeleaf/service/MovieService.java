package pl.tkaczyk.bookthymeleaf.service;

import org.springframework.stereotype.Service;
import pl.tkaczyk.bookthymeleaf.model.Movie;

import java.util.List;

@Service
public interface MovieService {
    List<Movie> showMovies();
    void addMovie(String title, String genre);
    void deleteMovie(String title);
}
