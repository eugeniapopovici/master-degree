package md.usarb.cinema.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Natalia Balan
 */

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moviesSeq")
    @SequenceGenerator(name = "moviesSeq", sequenceName = "movies_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "genre")
    private String genre;

    @Column(name = "stage_director")
    private String stageDirector;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "three_D")
    private Boolean threeD;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "movie_age")
    private Integer movieAge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStageDirector() {
        return stageDirector;
    }

    public void setStageDirector(String stageDirector) {
        this.stageDirector = stageDirector;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getThreeD() {
        return threeD;
    }

    public void setThreeD(Boolean threeD) {
        this.threeD = threeD;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getMovieAge() {
        return movieAge;
    }

    public void setMovieAge(Integer movieAge) {
        this.movieAge = movieAge;
    }
}
