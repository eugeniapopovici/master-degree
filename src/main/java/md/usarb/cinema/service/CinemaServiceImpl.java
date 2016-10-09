package md.usarb.cinema.service;

import flexjson.JSONSerializer;
import md.usarb.cinema.model.Booking;
import md.usarb.cinema.model.Cinema;
import md.usarb.cinema.model.Movie;
import md.usarb.cinema.repository.MovieDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;
import java.util.List;

@Path("")
public class CinemaServiceImpl implements ICinemaService {

    MovieDao movieDao = new MovieDao();


    @GET
    @Path("/home")
    @Produces( { MediaType.APPLICATION_JSON })
    public String getCustomer() {


        MovieDao mv = new MovieDao();
        List<Movie> movies = mv.findAll(Movie.class);
        LocalTime l = LocalTime.now();
        return new JSONSerializer().serialize(movies);

    }

//    @POST
    @GET
    @Path("/booking/movieId/{value}")
    @Consumes({ MediaType.TEXT_PLAIN })
    @Produces({ MediaType.APPLICATION_JSON })
    public String getCinemasByMovieId(@PathParam("value") Long movieId) {
        List<Cinema> cinemas = movieDao.loadCinemasByMovieId(movieId);

        return new JSONSerializer().exclude("*.class").serialize(cinemas);
    }

//    @POST
    @GET
    @Path("/booking/movieId/{movieId}/cinemaId/{cinemaId}")
    @Consumes({ MediaType.TEXT_PLAIN })
    @Produces({ MediaType.APPLICATION_JSON })
    public String getPerformancesByMovieAndCinemaIds(@PathParam("movieId") Long movieId, @PathParam("cinemaId") Long cinemaId) {
        List<Cinema> cinemas = movieDao.loadPerformancesByMovieAndCinemaIds(movieId, cinemaId);

        return new JSONSerializer().exclude("*.class").serialize(cinemas);
    }

    @POST
    @Path("/booking")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public String addBooking(Booking booking) {
        if (booking != null) {
            movieDao.create(booking);
        }

        return "Success!";
    }
}