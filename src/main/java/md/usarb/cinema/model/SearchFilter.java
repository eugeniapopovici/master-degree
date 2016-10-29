package md.usarb.cinema.model;

import md.usarb.cinema.utils.LocalDateTransformer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * @author Natalia Balan
 */
public class SearchFilter {

    private Long cinemaId;
    private String movieName;
    private Long performanceId;
    private Integer movieRating;
    private Boolean threeD;
    private Integer movieAge;
    private Date showingFromDate;
    private Date showingToDate;
    private String movieGenre;

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public Integer getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(Integer movieRating) {
        this.movieRating = movieRating;
    }

    public Boolean getThreeD() {
        return threeD;
    }

    public void setThreeD(Boolean threeD) {
        this.threeD = threeD;
    }

    public Integer getMovieAge() {
        return movieAge;
    }

    public void setMovieAge(Integer movieAge) {
        this.movieAge = movieAge;
    }

    public Date getShowingFromDate() {
        return showingFromDate;
    }

    public void setShowingFromDate(Date showingFromDate) {
        this.showingFromDate = showingFromDate;
    }

    public Date getShowingToDate() {
        return showingToDate;
    }

    public void setShowingToDate(Date showingToDate) {
        this.showingToDate = showingToDate;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public CriteriaQuery buildCrriteriaQuery(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Showing> criteriaQuery = criteriaBuilder.createQuery(Showing.class);
        Root<Showing> showing = criteriaQuery.from(Showing.class);
        criteriaQuery.multiselect(showing.get("movie")).distinct(true);

        List<Predicate> predicates = new ArrayList<>();
        if (!isNull(movieName)) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(showing.get("movie").get("name")),
                    "%" + movieName.toUpperCase() + "%"));
        } if (!isNull(movieAge)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(showing.get("movie").get("movieAge"), movieAge));
        }
        if (!isNull(movieRating)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(showing.get("movie").get("rating"), movieRating));
        }
        if (!isNull(threeD)) {
            predicates.add(criteriaBuilder.equal(showing.get("movie").get("threeD"), threeD));
        }
        if (!isNull(movieGenre)) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(showing.get("movie").get("genre")),
                     "%" + movieGenre.toUpperCase() + "%"));
        }
        if (!isNull(this.cinemaId)) {
            predicates.add(criteriaBuilder.equal(showing.get("cinema").get("id"), cinemaId));
        }
        if (!isNull(showingFromDate) && !isNull(showingToDate)) {
            predicates.add(criteriaBuilder.between(showing.get("showFromDate"),
                    LocalDateTransformer.getLocalDate(showingFromDate),  LocalDateTransformer.getLocalDate(showingToDate)));
        } else if (!isNull(showingFromDate)) {
            predicates.add(criteriaBuilder.between(showing.get("showFromDate"),
                    LocalDateTransformer.getLocalDate(showingFromDate), LocalDate.now()));
        } else if (!isNull(showingToDate)) {
            predicates.add(criteriaBuilder.between(showing.get("showFromDate"), LocalDate.now(),
                    LocalDateTransformer.getLocalDate(showingToDate)));
        }
        if (!isNull(performanceId)) {
            predicates.add(criteriaBuilder.equal(showing.get("performance").get("id"), performanceId));
        }
        if (!predicates.isEmpty()) {
            Predicate[] pr = new Predicate[predicates.size()];
            predicates.toArray(pr);
            criteriaQuery.where(pr);
        }
        return criteriaQuery;
    }
}
