package md.usarb.cinema.service;

import flexjson.JSONSerializer;
import md.usarb.cinema.model.*;
import md.usarb.cinema.repository.BookingDao;
import md.usarb.cinema.repository.CinemaDao;
import md.usarb.cinema.repository.MovieDao;
import md.usarb.cinema.repository.PerformanceDao;
import md.usarb.cinema.utils.CustomJsonDeserializer;
import md.usarb.cinema.utils.ExcludeTransformer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("")
public class CinemaServiceImpl {

   private MovieDao<Movie> movieDao = new MovieDao();
   private CinemaDao<Cinema> cinemaDao = new CinemaDao<>();
   private PerformanceDao<Performance> performanceDao = new PerformanceDao<>();
   private BookingDao<Booking> bookingDao = new BookingDao<>();

    @GET
    @Path("/movies/all")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public String loadAllMovies(){
        List<Movie> movies = movieDao.findAll(Movie.class);
        return new JSONSerializer().rootName("data").exclude("*.class", "calendarType", "chronology", "dayOfWeek",
                "dayOfYear", "era", "leapYear", "prolepticMonth").serialize(movies);
    }

    @GET
    @Path("/cinemas/all")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public String loadAllCinemas(){
        List<Cinema> movies = cinemaDao.findAll(Cinema.class);
        return new JSONSerializer().rootName("data").serialize(movies);
    }

    @GET
    @Path("/booking/all")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public String loadAllBookings(){
        List<Booking> bookings = bookingDao.getAllBookings(Booking.class);
        return new JSONSerializer().rootName("data").deepSerialize(bookings);
    }

//    @POST
//    @Path("/movies/filter")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
//    public String applyMovieFilter(@FormParam("movie") Long param) {
//        Map<String, Object> response = new HashMap<>();
//        response.put("success", "true");
//        Movie movie = movieDao.read(Movie.class, param);
//        response.put("data", movie != null ? Collections.singleton(movie) : Collections.emptyList());
//        return new JSONSerializer().exclude("*.class", "calendarType", "chronology", "dayOfWeek",
//                "dayOfYear", "era", "leapYear", "prolepticMonth").deepSerialize(response);
//    }

    @POST
    @Path("/movies/filter")
    @Produces( { MediaType.APPLICATION_JSON + ";charset=utf-8"})
    @Consumes(MediaType.APPLICATION_JSON)
    public String getMovies(final String json) {
//        SearchFilter searchFilter = new SearchFilter();
        SearchFilter searchFilter;
        if(json == null){
             searchFilter = new SearchFilter();
        }else{
             searchFilter = CustomJsonDeserializer.jsonDeserializer(json);
        }
        List<Showing> showings = movieDao.getFilteredMovies(searchFilter);
        List<Movie> movies = new ArrayList<>();
        for (Showing showing : showings) {
            movies.add(showing.getMovie());
        }
        return new JSONSerializer().transform(new ExcludeTransformer(), void.class).exclude("*.class" ,"calendarType", "chronology", "dayOfWeek",
                "dayOfYear", "era", "leapYear", "prolepticMonth").rootName("data").serialize(movies);
    }


    //    @POST
    @GET
    @Path("/booking/movieId/{value}")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON})
    public String getCinemasByMovieId(@PathParam("value") Long movieId) {
        List<Cinema> cinemas = cinemaDao.loadCinemasByMovieId(movieId);

        return new JSONSerializer().exclude("*.class").serialize(cinemas);
    }

    //    @POST
    @GET
    @Path("/booking/movieId/{movieId}/cinemaId/{cinemaId}")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON})
    public String getPerformancesByMovieAndCinemaIds(@PathParam("movieId") Long movieId, @PathParam("cinemaId") Long cinemaId) {
        List<Performance> performances = performanceDao.loadPerformancesByMovieAndCinemaIds(movieId, cinemaId);

        return new JSONSerializer().exclude("*.class").serialize(performances);
    }

    @POST
    @Path("/booking")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String addBooking(Booking booking) {
        if (booking != null) {
            bookingDao.create(booking);
        }

        return "Success!";
    }
}