package md.usarb.cinema.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Natalia Balan
 */
@Path("")
public class EmployeeServiceImpl {
    @GET
    @Path("/movies/all")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public String loadAllMovies(){
        return "test";
//        List<Movie> movies = movieDao.findAll(Movie.class);
//        return new JSONSerializer().rootName("data").exclude("*.class", "calendarType", "chronology", "dayOfWeek",
//                "dayOfYear", "era", "leapYear", "prolepticMonth").serialize(movies);
    }
}
