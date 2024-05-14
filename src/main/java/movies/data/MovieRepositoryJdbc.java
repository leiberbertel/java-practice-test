package movies.data;

import movies.model.Genre;
import movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieRepositoryJdbc implements MovieRepository {

    private static final RowMapper<Movie> movieMapper =
            (resultSet, i) -> new Movie
                    (
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("minutes"),
                            Genre.valueOf(resultSet.getString("genre")),
                            resultSet.getString("director")
                    );
    private final JdbcTemplate jdbcTemplate;

    public MovieRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Movie findById(long id) {
        Object[] args = {id};

        return jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id = ?", args, movieMapper);
    }

    @Override
    public Collection<Movie> findByName(String name) {
        Object[] args = {"%" + name.toLowerCase() + "%"};
        return jdbcTemplate.query("SELECT * FROM movies WHERE LOWER(name) LIKE ?", args, movieMapper);
    }

    @Override
    public Collection<Movie> findByDirector(String director) {
        Object[] args = {"%" + director.toLowerCase() + "%"};
        return jdbcTemplate.query("SELECT * FROM movies WHERE LOWER(director) LIKE ?", args, movieMapper);
    }

    @Override
    public Collection<Movie> findByTemplate(Movie movie) {
        StringBuilder sql = new StringBuilder("SELECT * FROM movies WHERE 1=1 ");
        List<Object> args = new ArrayList<>();

        if (movie.getName() != null) {
            sql.append("AND LOWER(name) = ? ");
            args.add(movie.getName().toLowerCase());
        }

        if (movie.getMinutes() < 0) {
            throw new IllegalArgumentException("duration must be greater or equal than zero.");
        }

        if (movie.getMinutes() != null) {
            sql.append("AND minutes <= ? ");
            args.add(movie.getMinutes());
        }

        if (movie.getGenre() != null) {
            sql.append("AND genre = ? ");
            args.add(movie.getGenre().toString());
        }

        if (movie.getDirector() != null) {
            sql.append("AND LOWER(director) = ? ");
            args.add(movie.getDirector().toLowerCase());
        }

        return jdbcTemplate.query(sql.toString(), args.toArray(), movieMapper);
    }


    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("SELECT * FROM movies", movieMapper);
    }

    @Override
    public void saveOrUpdate(Movie movie) {
        jdbcTemplate.update("INSERT INTO movies (name, minutes, genre, director) values (?, ?, ?, ?)",
                movie.getName(),
                movie.getMinutes(),
                movie.getGenre().toString(),
                movie.getDirector()
        );
    }

}
