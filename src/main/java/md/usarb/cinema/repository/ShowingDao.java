package md.usarb.cinema.repository;

import md.usarb.cinema.model.Showing;
import md.usarb.cinema.model.Cinema;
import md.usarb.cinema.model.Movie;

import javax.persistence.Query;
import java.util.List;

public class ShowingDao<T> extends GenericDao<T> {

    /**
     * Find {@link Showing} items
     * by {@link Cinema} and {@link Movie} items ids
     *
     * @param cinemaId
     * @param movieId
     * @return {@link List} of the {@link Showing} items
     */
    public List<Showing> loadShowingsByCinemaAndMovieIds(long cinemaId, long movieId) {
        Query q = entityManagerFactory.createEntityManager().createNamedQuery("showing.findShowingsByCinemaAndMovieIds");
        q.setParameter("cinemaId", cinemaId);
        q.setParameter("movieId", movieId);

        return q.getResultList();
    }
}
