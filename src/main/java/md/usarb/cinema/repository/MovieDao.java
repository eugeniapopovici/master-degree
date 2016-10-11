package md.usarb.cinema.repository;

import md.usarb.cinema.model.SearchFilter;

import javax.persistence.criteria.CriteriaQuery;
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
