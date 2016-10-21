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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @POST
    @Path("/movies/filter")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public String applyMovieFilter(@FormParam("movie") Long param) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", "true");
        Movie movie = movieDao.read(Movie.class, param);
        response.put("data", movie != null ? Collections.singleton(movie) : Collections.emptyList());
        return new JSONSerializer().exclude("*.class", "calendarType", "chronology", "dayOfWeek",
                "dayOfYear", "era", "leapYear", "prolepticMonth").deepSerialize(response);
    }

    @GET
    @Path("/home/showings")
    @Produces( { MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public String getMovies() {
//        SearchFilter searchFilter = new SearchFilter();
        String json = "{'movieName':'Hotii de geniu'," +
                "'cinemaId':'1'," +
                "'movieGenre':'Comedy'," +
                "'movieAge':'13'," +
                "'movieRating':'10'," +
                "'performanceId':'2'," +
                "'threeD': 'false', " +
                "'showingFromDate':'10/06/2016', "+
                "'showingToDate':'10/25/2016'" +
                "}";
        SearchFilter searchFilter;
        if(json == null){
             searchFilter = new SearchFilter();
        }else{
             searchFilter = CustomJsonDeserializer.jsonDeserializer(json);
        }
        List movies = movieDao.getFilteredMovies(searchFilter);
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