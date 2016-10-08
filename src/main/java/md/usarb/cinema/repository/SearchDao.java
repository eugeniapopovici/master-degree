package md.usarb.cinema.repository;

import md.usarb.cinema.model.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SearchDao<T> implements ISearchDao<T> {

    @PersistenceContext(name = "PGSQLPU")
    protected static EntityManager entityManager;

    public T searchForFilms(T t) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Film> criteriaQuery = criteriaBuilder.createQuery(Film.class);
//        Root<Film> root = criteriaQuery = criteriaQuery.from(Film.class);


        return null;
    }

    public static List<Movie> getMovies(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
        cq.from(Movie.class);
        return entityManager.createQuery(cq).getResultList();
    }

    public static void main(String[] args) {
getMovies();






    }
}

