package movies.service;

import movies.data.MovieRepository;
import movies.model.Genre;
import movies.model.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    private static List<Integer> getMoviesIds(Collection<Movie> movies) {
        return movies.stream().map(Movie::getId).collect(Collectors.toList());
    }

    @Before
    public void setup() {
        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION, "Christopher Nolan"),
                        new Movie(2, "Memento", 113, Genre.THRILLER, "Christopher nolan"),
                        new Movie(3, "Super 8", 112, Genre.THRILLER, "J. J. Abrams"),
                        new Movie(4, "Scream", 111, Genre.ACTION, "Wes Craven"),
                        new Movie(5, "Matrix", 136, Genre.ACTION, "Lana Wachowsk")
                )
        );
    }

    @Test
    public void return_movies_by_genre() {
        Collection<Movie> movies = movieService.findMoviesByGenre(Genre.THRILLER);

        assertEquals(getMoviesIds(movies), Arrays.asList(2, 3));
    }

    @Test
    public void return_movies_by_length() {
        Collection<Movie> movies = movieService.findMoviesByLength(113);

        assertEquals(getMoviesIds(movies), Arrays.asList(2, 3, 4));
    }

    @Test
    public void return_movies_by_name() {
        Collection<Movie> movies = movieService.findMovieByName("s");

        assertEquals(Arrays.asList(3, 4), getMoviesIds(movies));
    }

    @Test
    public void return_movies_by_director() {
        Collection<Movie> movies = movieService.findMovieByDirector("s");

        assertEquals(Arrays.asList(1, 2, 3, 4, 5), getMoviesIds(movies));
    }

    @Test
    public void return_movies_by_template() {
        Movie template = new Movie(null, 136, Genre.ACTION, null);
        Collection<Movie> movies = movieService.findMoviesByTemplate(template);

        assertEquals(Arrays.asList(4, 5), getMoviesIds(movies));
    }

    @Test
    public void return_exception_when_minutes_is_number_negative() {
        Movie template = new Movie(null, -16, Genre.ACTION, null);
        assertThrows(
                IllegalArgumentException.class,
                () -> movieService.findMoviesByTemplate(template)
        );
    }
}