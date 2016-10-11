package md.usarb.cinema.repository;

import java.util.List;

public interface ISearchDao<T, PK> {

    T searchForFilms(T t);

    List<T> getAll(Class<T> clazz);
}
