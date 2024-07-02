package gn.diallo.demohexagradle.adapters.out.postgresJDBC.repositories;

import gn.diallo.demohexagradle.adapters.out.postgresJDBC.entities.MovieEntity;
import gn.diallo.demohexagradle.domain.Movie;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
    @Query("SELECT m FROM movies m WHERE m.title = :title")
    Optional<Movie> findAllByTitle(@Param("title") String title);
}
