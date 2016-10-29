package md.usarb.cinema.repository;

import md.usarb.cinema.model.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MovieDao<T> extends GenericDao<T> {

    public List getFilteredMovies(SearchFilter searchFilter) {
        CriteriaQuery criteriaQuery = searchFilter.buildCrriteriaQuery(entityManagerFactory.createEntityManager());
        return entityManagerFactory.createEntityManager().createQuery(criteriaQuery).getResultList();
    }

}
