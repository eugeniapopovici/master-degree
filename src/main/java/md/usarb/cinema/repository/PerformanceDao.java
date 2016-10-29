package md.usarb.cinema.repository;

import md.usarb.cinema.model.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class PerformanceDao<T> extends GenericDao<T> {

    /**
     * Load all {@link Performance} items by {@link Movie} and {@link Cinema} items ids
     * all items is loaded from {@link Showing} entity joined with {@link Performance}
     *
     * @param movieId
     * @param cinemaId
     * @return {@link List} of the {@link Cinema} items
     */
    public List<T> loadPerformancesByMovieAndCinemaIds(Long movieId, Long cinemaId) {
        Query q = entityManagerFactory.createEntityManager().createNamedQuery("performance.loadListByMovieAndCinemaIds");
        q.setParameter("movieId", movieId);
        q.setParameter("cinemaId", cinemaId);
        return q.getResultList();
    }
}
