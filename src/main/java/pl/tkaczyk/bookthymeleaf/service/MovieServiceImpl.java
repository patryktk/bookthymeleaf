package pl.tkaczyk.bookthymeleaf.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.tkaczyk.bookthymeleaf.model.Movie;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Movie> showMovies() {
        List<Movie> movieList = new ArrayList<>();
        Movie[] movies = restTemplate.getForObject("https://pkowaleckimoviesapi.herokuapp.com/movie/allMovies", Movie[].class);
        return getMovieList(movieList, movies);
    }

    @Override
    public void addMovie(String title, String genre) {
        String url = "https://pkowaleckimoviesapi.herokuapp.com/movie/addMovie";
        Movie movie1 = new Movie();
        movie1.setMovieName(title);
        movie1.setMovieGenre(genre);
        ResponseEntity<Movie> responseEntity = restTemplate.postForEntity(url, movie1, Movie.class);
    }

    @Override
    public void deleteMovie(String title) {
        String url = "https://pkowaleckimoviesapi.herokuapp.com/movie/deleteMovie/" + title;
        restTemplate.delete(url);
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
