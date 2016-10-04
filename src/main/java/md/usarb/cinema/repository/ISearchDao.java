package md.usarb.cinema.repository;

public interface ISearchDao<T> {

    T searchForFilms(T t);

}
