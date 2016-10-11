package md.usarb.cinema.service;

import flexjson.JSONSerializer;
import md.usarb.cinema.model.Movie;
import md.usarb.cinema.model.SearchFilter;
import md.usarb.cinema.repository.MovieDao;
import md.usarb.cinema.utils.ExcludeTransformer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;
import java.util.List;

@Path("")
public class CinemaServiceImpl implements ICinemaService {

   private MovieDao movieDao = new MovieDao();

    @GET
    @Path("/home")
    @Produces( { MediaType.APPLICATION_JSON })
    public String getCustomer() {

        List<Movie> movies = movieDao.findAll(Movie.class);
        LocalTime l = LocalTime.now();
        return new JSONSerializer().serialize(movies);

    }

    @GET
    @Path("/home/showings")
    @Produces( { MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public String getMovies() {
        SearchFilter searchFilter = new SearchFilter();
//        searchFilter.setMovieName("Hotii de geniu");
//        searchFilter.setCinemaName("Multiplex");
//        searchFilter.setMovieAge(13);
        searchFilter.setMovieRating(9);
//        searchFilter.setPerformanceStartTime(LocalTime.of(13, 8, 42));
//        searchFilter.setPerformanceEndTime(LocalTime.of(16, 8, 42));
//        searchFilter.setThreeD(false);
//        searchFilter.setShowingFromDate(LocalDate.of(2016, 10, 6));
//        searchFilter.setShowingToDate(LocalDate.of(2016, 10, 25));
//        searchFilter.setMovieGenre("Comedy");
        List movies = movieDao.loadMovies(searchFilter);
        return new JSONSerializer().transform(new ExcludeTransformer(), void.class).exclude("*.class").rootName("data").serialize(movies);
    }


}