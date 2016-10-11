package md.usarb.cinema.service;

import flexjson.JSONSerializer;
import md.usarb.cinema.model.Booking;
import md.usarb.cinema.model.Cinema;
import md.usarb.cinema.model.Movie;
import md.usarb.cinema.model.SearchFilter;
import md.usarb.cinema.repository.MovieDao;
import md.usarb.cinema.utils.ExcludeTransformer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;
import java.util.List;

@Path("")
public class CinemaServiceImpl implements ICinemaService {

   private MovieDao movieDao = new MovieDao();

    @GET
    @Path("/home")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCustomer() {

        List<Movie> movies = movieDao.findAll(Movie.class);
        LocalTime l = LocalTime.now();
        return new JSONSerializer().serialize(movies);

    }

    @POST
    @Path("/movies/filter")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public String applyMovieFilter(@FormParam("textfield-1014-inputEl") String param) {
        String success = "true";
        return new JSONSerializer().rootName("data").serialize(success);
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


    //    @POST
    @GET
    @Path("/booking/movieId/{value}")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON})
    public String getCinemasByMovieId(@PathParam("value") Long movieId) {
        List<Cinema> cinemas = movieDao.loadCinemasByMovieId(movieId);

        return new JSONSerializer().exclude("*.class").serialize(cinemas);
    }

    //    @POST
    @GET
    @Path("/booking/movieId/{movieId}/cinemaId/{cinemaId}")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON})
    public String getPerformancesByMovieAndCinemaIds(@PathParam("movieId") Long movieId, @PathParam("cinemaId") Long cinemaId) {
        List<Cinema> cinemas = movieDao.loadPerformancesByMovieAndCinemaIds(movieId, cinemaId);

        return new JSONSerializer().exclude("*.class").serialize(cinemas);
    }

    @POST
    @Path("/booking")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String addBooking(Booking booking) {
        if (booking != null) {
            movieDao.create(booking);
        }

        return "Success!";
    }
}