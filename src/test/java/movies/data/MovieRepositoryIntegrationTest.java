package movies.data;

import movies.model.Genre;
import movies.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class MovieRepositoryIntegrationTest {

    private final DataSource dataSource =
            new DriverManagerDataSource(
                    "jdbc:mysql://localhost:3306/testJava?createDatabaseIfNotExist=true",
                    "root", "");

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    private final MovieRepositoryJdbc movieRepository = new MovieRepositoryJdbc(jdbcTemplate);

    @Before
    public void setup() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("sql-scripts/test-data.sql"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void load_all_movies() {
        Collection<Movie> movies = movieRepository.findAll();

        assertEquals(movies, Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION, "Christopher Nolan"),
                new Movie(2, "Memento", 113, Genre.THRILLER, "Christopher nolan"),
                new Movie(3, "Matrix", 136, Genre.ACTION, "Lana Wachowsk")
        ));
    }

    @Test
    public void load_movie_by_id() {
        Movie movie = movieRepository.findById(1);
        assertEquals(movie, new Movie(1, "Dark Knight", 152, Genre.ACTION, "Christopher Nolan"));
    }

    @Test
    public void insert_a_movie() {
        Movie movie = new Movie(4,"Gran Turismo", 140, Genre.ACTION, "Neill Blomkamp");
        movieRepository.saveOrUpdate(movie);

        Movie movieFromDb = movieRepository.findById(4);
        assertEquals(movieFromDb.getId(), movie.getId());
    }

    @Test
    public void load_movies_by_name() {
        Collection<Movie> movies = movieRepository.findByName("M");
        assertEquals(movies, Arrays.asList(
                new Movie(2,"Memento", 113, Genre.THRILLER, "Christopher Nolan"),
                new Movie(3, "Matrix", 136, Genre.ACTION, "Lana Wachowsk")
        ));
    }

    @Test
    public void load_movies_by_director() {
        Collection<Movie> movies = movieRepository.findByDirector("CH");
        assertEquals(movies, Arrays.asList(
                new Movie(1,"Dark Knight", 152, Genre.ACTION, "Christopher Nolan"),
                new Movie(2,"Memento", 113, Genre.THRILLER, "Christopher Nolan"),
                new Movie(3, "Matrix", 136, Genre.ACTION, "Lana Wachowsk")
        ));
    }

    @Test
    public void load_movies_by_template() {
        Movie template = new Movie(null, 152, null, null);
        Collection<Movie> movies = movieRepository.findByTemplate(template);
        assertEquals(Arrays.asList(
                new Movie(1,"Dark Knight", 152, Genre.ACTION, "Christopher Nolan"),
                new Movie(2,"Memento", 113, Genre.THRILLER, "Christopher Nolan"),
                new Movie(3, "Matrix", 136, Genre.ACTION, "Lana Wachowsk")
        ), movies);
    }

    @After
    public void tearDown() {
        jdbcTemplate.execute("DELETE FROM movies");
        jdbcTemplate.execute("ALTER TABLE movies AUTO_INCREMENT = 1");
    }
}