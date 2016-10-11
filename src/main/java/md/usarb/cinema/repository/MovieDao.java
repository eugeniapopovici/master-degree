package md.usarb.cinema.repository;

import md.usarb.cinema.model.SearchFilter;

import javax.persistence.criteria.CriteriaQuery;
import md.usarb.cinema.model.Cinema;
import md.usarb.cinema.model.Movie;
import md.usarb.cinema.model.Performance;
import md.usarb.cinema.model.Showing;

import java.io.Serializable;
import java.util.List;

public class MovieDao<T, PK extends Serializable> extends GenericDao<T, PK> implements IMovieDao<T, PK> {

    @Override
    public List<T> findAll(Class<T> clazz) {
        return super.findAll(clazz);
    }


    public List loadMovies(SearchFilter searchFilter) {
        CriteriaQuery criteriaQuery = searchFilter.buildCrriteriaQuery(entityManager);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}


    /**
     * Load all {@link Cinema} items by {@link Movie} item id
     * all items is loaded from {@link Showing} entity joined with {@link Cinema}
     *
     * @param id
     * @return {@link List} of the {@link Cinema} items
     */
    public List<T> loadCinemasByMovieId(long id) {
        return super.loadItemsById(id, "cinema.loadListByMovieId");
    }

    /**
     * Load all {@link Performance} items by {@link Movie} and {@link Cinema} items ids
     * all items is loaded from {@link Showing} entity joined with {@link Performance}
     *
     * @param movieId
     * @param cinemaId
     * @return {@link List} of the {@link Cinema} items
     */
    public List<T> loadPerformancesByMovieAndCinemaIds(long movieId, long cinemaId) {
        return super.loadItemsByTwoIds(movieId, cinemaId, "performance.loadListByMovieAndCinemaIds");
    }
}
