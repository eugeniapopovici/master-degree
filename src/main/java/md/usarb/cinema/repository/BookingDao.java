package md.usarb.cinema.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class BookingDao<T> extends GenericDao<T> {

    public List<T> getAllBookings(Class<T> tClass){
        CriteriaBuilder criteriaBuilder = entityManagerFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        criteriaQuery.from(tClass);
        return entityManagerFactory.createEntityManager().createQuery(criteriaQuery).getResultList();
    }

}
