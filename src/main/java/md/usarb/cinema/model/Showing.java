package md.usarb.cinema.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Natalia Balan
 */
@Entity
@Table(name = "showings")
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "showingsSeq")
    @SequenceGenerator(name = "showingsSeq", sequenceName = "showings_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Cinema cinema;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Movie movie;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Performance performance;

    @Column(name = "show_from_date")
    private LocalDate showFromDate;

    @Column(name = "show_to_date")
    private LocalDate showToDate;


    public Showing() {

    }

    public Showing(Movie movie) {
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public LocalDate getShowFromDate() {
        return showFromDate;
    }

    public void setShowFromDate(LocalDate showFromDate) {
        this.showFromDate = showFromDate;
    }

    public LocalDate getShowToDate() {
        return showToDate;
    }

    public void setShowToDate(LocalDate showToDate) {
        this.showToDate = showToDate;
    }

}
