package md.usarb.cinema.model;

import javax.persistence.*;

/**
 * @author Natalia Balan
 */
@Entity
@Table(name = "showings")
public class Showing {

    @Id
//    @SequenceGenerator(name = "showingsSeq", sequenceName = "showings_id_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "showingsSeq")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
