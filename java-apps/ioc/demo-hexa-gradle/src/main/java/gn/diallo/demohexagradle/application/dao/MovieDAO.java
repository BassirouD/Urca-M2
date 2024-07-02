package gn.diallo.demohexagradle.application.dao;

import gn.diallo.demohexagradle.application.dto.NewMovieDto;
import gn.diallo.demohexagradle.domain.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDAO {
    Optional<Movie> findMovieByTitle(String title);

    List<Movie> findAllMovies();

    void saveMovie(NewMovieDto movie);

    void updateMovie(Movie movie);

    void deleteMovie(Movie movie);
}
