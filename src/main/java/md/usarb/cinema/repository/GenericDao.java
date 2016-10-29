package md.usarb.cinema.repository;

import md.usarb.cinema.model.Movie;

import javax.persistence.EntityManager;
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

    public EntityManager entityManager = Persistence.createEntityManagerFactory("PGSQLPU").createEntityManager();

    public T create(T t) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(t);
        this.entityManager.getTransaction().commit();
        return t;
    }

    public T read(Class<T> tClass, Long id) {
        return this.entityManager.find(tClass, id);
    }

    public T update(T t) {
        return this.entityManager.merge(t);
    }

    public void delete(T t) {
        t = this.entityManager.merge(t);
        this.entityManager.remove(t);
    }

    public T find(Class<T> clazz, Object o) {
        return entityManager.find(clazz, o);
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
}
