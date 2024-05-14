package movies.data;

import movies.model.Movie;

import java.util.Collection;

public interface MovieRepository {

    Movie findById(long id);

    Collection<Movie> findByName(String name);

    Collection<Movie> findByDirector(String director);

    Collection<Movie> findByTemplate(Movie movie);

    Collection<Movie> findAll();

    void saveOrUpdate(Movie movie);
}
