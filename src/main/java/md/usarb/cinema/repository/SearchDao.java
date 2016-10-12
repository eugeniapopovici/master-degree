package md.usarb.cinema.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class SearchDao<T> extends  GenericDao<T> {


    public T searchForFilms(T t) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Film> criteriaQuery = criteriaBuilder.createQuery(Film.class);
//        Root<Film> root = criteriaQuery = criteriaQuery.from(Film.class);


        return null;
    }
    public List<T> getAll(Class<T> clazz) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}

