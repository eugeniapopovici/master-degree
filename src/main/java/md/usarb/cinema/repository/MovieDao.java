package md.usarb.cinema.repository;

import java.io.Serializable;
import java.util.List;

public class MovieDao<T, PK extends Serializable> extends GenericDao<T, PK> implements IMovieDao<T, PK> {

    @Override
    public List<T> findAll(Class<T> clazz) {
        return super.findAll(clazz);
    }
}
