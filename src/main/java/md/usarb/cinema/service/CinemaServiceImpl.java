package md.usarb.cinema.service;

import flexjson.JSONSerializer;
import md.usarb.cinema.model.*;
import md.usarb.cinema.repository.*;
import md.usarb.cinema.utils.CustomJsonDeserializer;
import md.usarb.cinema.utils.ExcludeTransformer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("")
public class CinemaServiceImpl {

   private MovieDao<Movie> movieDao = new MovieDao();
   private CinemaDao<Cinema> cinemaDao = new CinemaDao<>();
   private PerformanceDao<Performance> performanceDao = new PerformanceDao<>();
    private BookingDao<Booking> bookingDao = new BookingDao<>();
   private CategoryDao<Category> categoryDao = new CategoryDao<>();

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

    @GET
    @Path("/categories/all")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public String loadAllCategories(){
        List<Category> categories = categoryDao.findAll(Category.class);
        return new JSONSerializer().rootName("data").serialize(categories);
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


    @GET
    @Path("/booking/cinemas/movieId/{value}")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON})
    public String getCinemasByMovieId(@PathParam("value") Long movieId) {
        List<Cinema> cinemas = cinemaDao.loadCinemasByMovieId(movieId);

        return new JSONSerializer().include("id", "cinemaName").exclude("*").serialize(cinemas);
    }

    @GET
    @Path("/booking/performances?_dc={dc}&movieId={movieId}&cinemaId={cinemaId}&page=1&start=0&limit=25")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON})
    public String getPerformancesByMovieAndCinemaIds(@PathParam("dc") Long dc, @PathParam("movieId") Long movieId, @PathParam("cinemaId") Long cinemaId) {
//        List<Performance> performances = performanceDao.loadPerformancesByMovieAndCinemaIds(movieId, cinemaId);

        System.out.println("____________________________________________");
        System.out.println(movieId);
        System.out.println("____________________________________________");

        return new JSONSerializer().exclude("*.class").serialize(null);
//        return new JSONSerializer().exclude("*.class").serialize(performances);
    }

    @POST
    @Path("/booking")
    @Produces( { MediaType.APPLICATION_JSON + ";charset=utf-8"})
    @Consumes(MediaType.APPLICATION_JSON)
    public String addBooking(final String json) {
        HashMap<String, Object> response = new HashMap<>();
        Boolean success = false;
        String message = "Your booking is not complete!";

        if (json != null) {
            Booking booking = CustomJsonDeserializer.jsonBookingDeserializer(json);

            if (!booking.isEmpty()) {
                Booking bookingNew = bookingDao.create(booking);

                success = true;
                message = "Your booking was successfully realised!";

                response.put("booking", bookingNew);
            }
        }

        response.put("success", success);
        response.put("message", message);
        return new JSONSerializer().serialize(response);
    }
}