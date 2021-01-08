package pl.tkaczyk.bookthymeleaf.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.tkaczyk.bookthymeleaf.model.Movie;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    RestTemplate restTemplate = new RestTemplate();

    public MovieService() {
    }

    public List<Movie> allMovies() {
        List<Movie> movieList = new ArrayList<>();
        Movie[] movies = restTemplate.getForObject("https://pkowaleckimoviesapi.herokuapp.com/movie/allMovies", Movie[].class);
        return getMovieList(movieList, movies);
    }

    private List<Movie> getMovieList(List<Movie> movieList, Movie[] movies) {
        for (Movie movie1 : movies) {
            Movie newMovie = new Movie();
            newMovie.setMovieId(movie1.getMovieId());
            newMovie.setMovieName(movie1.getMovieName());
            newMovie.setMovieGenre(movie1.getMovieGenre());
            movieList.add(newMovie);
        }
        return movieList;
    }
}
