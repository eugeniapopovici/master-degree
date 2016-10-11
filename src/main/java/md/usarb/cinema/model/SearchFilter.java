package md.usarb.cinema.model;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * @author Natalia Balan
 */
public class SearchFilter {

    private String cinemaName;
    private String movieName;
    private LocalTime performanceStartTime;
    private LocalTime performanceEndTime;
    private Integer movieRating;
    private Boolean threeD;
    private Integer movieAge;
    private LocalDate showingFromDate;
    private LocalDate showingToDate;
    private String movieGenre;

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalTime getPerformanceStartTime() {
        return performanceStartTime;
    }

    public void setPerformanceStartTime(LocalTime performanceStartTime) {
        this.performanceStartTime = performanceStartTime;
    }

    public LocalTime getPerformanceEndTime() {
        return performanceEndTime;
    }

    public void setPerformanceEndTime(LocalTime performanceEndTime) {
        this.performanceEndTime = performanceEndTime;
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

    public LocalDate getShowingFromDate() {
        return showingFromDate;
    }

    public void setShowingFromDate(LocalDate showingFromDate) {
        this.showingFromDate = showingFromDate;
    }

    public LocalDate getShowingToDate() {
        return showingToDate;
    }

    public void setShowingToDate(LocalDate showingToDate) {
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
            predicates.add(criteriaBuilder.like(showing.get("movie").get("name"), movieName));
        } if (!isNull(movieAge)) {
            predicates.add(criteriaBuilder.equal(showing.get("movie").get("movieAge"), movieAge));
        }
        if (!isNull(movieRating)) {
            predicates.add(criteriaBuilder.equal(showing.get("movie").get("rating"), movieRating));
        }
        if (!isNull(threeD)) {
            predicates.add(criteriaBuilder.equal(showing.get("movie").get("threeD"), threeD));
        }
        if (!isNull(movieGenre)) {
            predicates.add(criteriaBuilder.equal(showing.get("movie").get("genre"), movieGenre));
        }
        if (!isNull(this.cinemaName)) {
            predicates.add(criteriaBuilder.like(showing.get("cinema").get("cinemaName"), cinemaName));
        }
        if (!isNull(showingFromDate) && !isNull(showingToDate)) {
            predicates.add(criteriaBuilder.between(showing.get("showFromDate"), showingFromDate, showingToDate));
        } else if (!isNull(showingFromDate)) {
            predicates.add(criteriaBuilder.between(showing.get("showFromDate"), showingFromDate, LocalDate.now()));
        } else if (!isNull(showingToDate)) {
            predicates.add(criteriaBuilder.between(showing.get("showFromDate"), LocalDate.now(), showingToDate));
        }
        if (!isNull(performanceStartTime) && !isNull(performanceEndTime)) {
            predicates.add(criteriaBuilder.between(showing.get("performance").get("performanceStartTime"), performanceStartTime, performanceEndTime));
        } else if (!isNull(performanceStartTime)) {
            predicates.add(criteriaBuilder.between(showing.get("performance").get("performanceStartTime"), performanceStartTime, LocalTime.now()));
        } else if (!isNull(performanceEndTime)) {
            predicates.add(criteriaBuilder.between(showing.get("performance").get("performanceStartTime"), LocalTime.now(), performanceEndTime));
        }
        if (!predicates.isEmpty()) {
            Predicate[] pr = new Predicate[predicates.size()];
            predicates.toArray(pr);
            criteriaQuery.where(pr);
        }
        return criteriaQuery;
    }
}
