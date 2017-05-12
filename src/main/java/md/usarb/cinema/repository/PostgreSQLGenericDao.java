package md.usarb.cinema.repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class PostgreSQLGenericDao<T> {

    public static EntityManagerFactory entityManagerFactory;

    public T read(Class<T> tClass, Long id) {
        return entityManagerFactory.createEntityManager().find(tClass, id);
    }

    public T find(Class<T> clazz, Object o) {
        return entityManagerFactory.createEntityManager().find(clazz, o);
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
}
