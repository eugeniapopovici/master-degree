package md.usarb.cinema.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SearchDao<T> implements ISearchDao<T> {

    @PersistenceContext(name = "PGSQLPU")
    protected EntityManager entityManager;

    public T searchForFilms(T t) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Film> criteriaQuery = criteriaBuilder.createQuery(Film.class);
//        Root<Film> root = criteriaQuery = criteriaQuery.from(Film.class);


        return null;
    }
}
