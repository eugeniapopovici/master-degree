package md.usarb.cinema.repository;

import md.usarb.cinema.model.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GenericDao<T> {

    public static EntityManagerFactory entityManagerFactory;

    public T create(T t) {
        entityManagerFactory.createEntityManager().persist(t);
        return t;
    }

    public T read(Class<T> tClass, Long id) {
        return entityManagerFactory.createEntityManager().find(tClass, id);
    }

    public T update(T t) {
        return entityManagerFactory.createEntityManager().merge(t);
    }

    public void delete(T t) {
        t = entityManagerFactory.createEntityManager().merge(t);
        entityManagerFactory.createEntityManager().remove(t);
    }

    /**
     * Finds all objects of an entity class.
     *
     * @param clazz the entity class.
     * @return
     */
    public List<T> findAll(Class<T> clazz) {
        CriteriaBuilder cb = entityManagerFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        cq.from(clazz);
        return entityManagerFactory.createEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Load {@link List} of items by item id
     *
     * @param itemId
     * @param queryName- is query for execute
     * @return {@link List} of the items
     */
    List<T> loadItemsById(Long itemId, String queryName) {
        Query q = entityManagerFactory.createEntityManager().createNamedQuery(queryName);
        q.setParameter("itemId", itemId);
        return q.getResultList();
    }


//                      WTF?
//    /**
//     * Load {@link List} of items by item id
//     *
//     * @param firstId
//     * @param secondId
//     * @param queryName- is query for execute
//     * @return {@link List} of the items
//     */
//    List<T> loadItemsByTwoIds(Long firstId, Long secondId, String queryName) {
//        Query q = entityManager.createNamedQuery(queryName);
//        q.setParameter("firstId", firstId);
//        q.setParameter("secondId", secondId);
//        return q.getResultList();
//    }

}
