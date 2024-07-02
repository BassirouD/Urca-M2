package gn.diallo.demohexagradle.adapters.out.postgresJDBC;

import gn.diallo.demohexagradle.adapters.out.postgresJDBC.entities.MovieEntity;
import gn.diallo.demohexagradle.adapters.out.postgresJDBC.repositories.MovieRepository;
import gn.diallo.demohexagradle.application.dao.MovieDAO;
import gn.diallo.demohexagradle.application.dto.NewMovieDto;
import gn.diallo.demohexagradle.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MovieDaoAdapter implements MovieDAO {
    private final MovieRepository movieRepository;

    @Override

    public Optional<Movie> findMovieByTitle(String title) {
        return movieRepository.findAllByTitle(title);
    }

    @Override
    public List<Movie> findAllMovies() {
        return ((List<MovieEntity>) movieRepository.findAll())
                .stream()
                .map(movieEntity -> new
                        Movie(
                        movieEntity.id(),
                        movieEntity.title(),
                        movieEntity.description(),
                        movieEntity.release(),
                        movieEntity.director()
                )).toList();
    }

    @Override
    public void saveMovie(NewMovieDto movie) {
        movieRepository.save(new MovieEntity(
                null,
                movie.title(),
                movie.description(),
                movie.release(),
                movie.director(),
                null
        ));
    }

    @Override
    public void updateMovie(Movie movie) {
        movieRepository.save(new MovieEntity(
                movie.id(),
                movie.title(),
                movie.description(),
                movie.release(),
                movie.director(),
                null
        ));
    }

    @Override
    public void deleteMovie(Movie movie) {
        //movieRepository.delete(movie);
    }
}
