package md.usarb.cinema.service;

import flexjson.JSONSerializer;
import md.usarb.cinema.model.Movie;
import md.usarb.cinema.repository.MovieDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;
import java.util.List;

@Path("")
public class CinemaServiceImpl implements ICinemaService {



    @GET
    @Path("/home")
    @Produces( { MediaType.APPLICATION_JSON })
    public String getCustomer() {


        MovieDao mv = new MovieDao();
        List<Movie> movies = mv.findAll(Movie.class);
        LocalTime l = LocalTime.now();
        return new JSONSerializer().serialize(movies);

    }


}