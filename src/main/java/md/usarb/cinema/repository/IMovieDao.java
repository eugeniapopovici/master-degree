package md.usarb.cinema.repository;

import java.io.Serializable;
import java.util.List;

interface IMovieDao<T, PK extends Serializable> extends IGenericDao<T, PK> {

    T create(T t);

    T update(T t);

    void delete(T t);

    List<T> findAll(Class<T> clazz);

}
