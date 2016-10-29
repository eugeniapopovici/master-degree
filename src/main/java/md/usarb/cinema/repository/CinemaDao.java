package md.usarb.cinema.repository;

import md.usarb.cinema.model.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CinemaDao<T> extends GenericDao<T> {

    /**
     * Load all {@link Cinema} items by {@link Movie} item id
     * all items is loaded from {@link Showing} entity joined with {@link Cinema}
     *
     * @param movieId
     * @return {@link List} of the {@link Cinema} items
     */
    public List<T> loadCinemasByMovieId(Long movieId) {
        Query q = entityManagerFactory.createEntityManager().createNamedQuery("cinema.loadListByMovieId");
        q.setParameter("itemId", movieId);
        return q.getResultList();
    }

}
