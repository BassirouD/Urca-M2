package gn.diallo.demohexagradle.application.usecases;

import gn.diallo.demohexagradle.application.dao.MovieDAO;
import gn.diallo.demohexagradle.application.dto.NewMovieDto;
import gn.diallo.demohexagradle.domain.Movie;
import gn.diallo.demohexagradle.infrastructure.exceptions.MovieAlReadyExistsException;
import gn.diallo.demohexagradle.infrastructure.exceptions.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieUseCase {
    private final MovieDAO movieDAO;

    public String saveMovie(NewMovieDto newMovieDto) throws MovieAlReadyExistsException {
        var isPresent = movieDAO.findMovieByTitle(newMovieDto.title()).isPresent();
        if (isPresent) throw new MovieAlReadyExistsException("Movie already exists");
        movieDAO.saveMovie(newMovieDto);
        return "Movie saved successfully";
    }

    public List<Movie> getAllMovies() {
        return movieDAO.findAllMovies();
    }

    public String updateMovie(Movie movie) throws MovieNotFoundException {
        var isPresent = movieDAO.findMovieByTitle(movie.title()).isPresent();
        if (!isPresent) throw new MovieNotFoundException("This movie does not exist");
        movieDAO.updateMovie(movie);
        return "Movie updated successfully";
    }

    public Movie getMovieByTitle(String title) {
        return movieDAO.findMovieByTitle(title).orElseThrow(() -> new MovieNotFoundException("This movie does not exist"));
    }
}
