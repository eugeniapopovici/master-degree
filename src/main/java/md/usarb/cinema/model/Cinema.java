package md.usarb.cinema.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author Natalia Balan
 */

@Entity
@Table(name = "cinemas")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cinemasSeq")
    @SequenceGenerator(name = "cinemasSeq", sequenceName = "cinemas_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "cinema_name")
    private String cinemaName;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address cinemaAddress;

    @Column(name = "cinema_phone")
    private String cinemaPhone;

    @OneToMany(mappedBy = "cinema")
    private List<Showing> showings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public Address getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(Address cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }

    public String getCinemaPhone() {
        return cinemaPhone;
    }

    public void setCinemaPhone(String cinemaPhone) {
        this.cinemaPhone = cinemaPhone;
    }

    public List<Showing> getShowings() {
        return showings;
    }

    public void setShowings(List<Showing> showings) {
        this.showings = showings;
    }
}
