package md.usarb.cinema.model;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * @author Natalia Balan
 */
@Entity
@Table(name = "performances")
@NamedQueries({
        @NamedQuery(name = "performance.loadListByMovieAndCinemaIds",
                query = "SELECT C FROM Cinema C WHERE C.id IN " +
                            "(SELECT S.cinema.id FROM Showing S " +
                                "WHERE S.movie.id = (:firstId) AND S.cinema.id = (:secondId))"
        )
})
public class Performance {

    @Id
//    @SequenceGenerator(name = "performancesSeq", sequenceName = "performances_id_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "performancessSeq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "performance_start_time")
    private LocalTime performanceStartTime;

    @Column(name = "performance_end_time")
    private LocalTime performanceEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
