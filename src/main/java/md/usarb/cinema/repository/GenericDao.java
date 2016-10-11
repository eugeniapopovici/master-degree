package md.usarb.cinema.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class GenericDao<T, PK extends Serializable> implements IGenericDao<T, PK> {

    public EntityManager entityManager = Persistence.createEntityManagerFactory("PGSQLPU").createEntityManager();

    public T create(T t) {
        this.entityManager.persist(t);
        return t;
    }

    public T read(PK id) {
        return this.entityManager.find(null, id);
    }

    public T update(T t) {
        return this.entityManager.merge(t);
    }

    public void delete(T t) {
        t = this.entityManager.merge(t);
        this.entityManager.remove(t);
    }

    /**
     * Finds all objects of an entity class.
     *
     * @param clazz the entity class.
     * @return
     */
    public List<T> findAll(Class<T> clazz) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        cq.from(clazz);
        return entityManager.createQuery(cq).getResultList();
    }

    /**
     * Load {@link List} of items by item id
     *
     * @param id
     * @param queryName- is query for execute
     * @return {@link List} of the items
     */
    List<T> loadItemsById(long id, String queryName) {
        Query q = entityManager.createNamedQuery(queryName);
        q.setParameter("itemId", id);
        return q.getResultList();
    }

    /**
     * Load {@link List} of items by item id
     *
     * @param firstId
     * @param secondId
     * @param queryName- is query for execute
     * @return {@link List} of the items
     */
    List<T> loadItemsByTwoIds(long firstId, long secondId, String queryName) {
        Query q = entityManager.createNamedQuery(queryName);
        q.setParameter("firstId", firstId);
        q.setParameter("secondId", secondId);
        return q.getResultList();
    }

}
