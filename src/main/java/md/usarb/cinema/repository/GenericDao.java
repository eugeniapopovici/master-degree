package md.usarb.cinema.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public class GenericDao<T, PK extends Serializable> implements IGenericDao<T, PK> {

    @PersistenceContext(name = "PGSQLPU")
    protected EntityManager entityManager;

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

}
