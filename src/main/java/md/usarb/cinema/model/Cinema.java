package md.usarb.cinema.model;

import javax.persistence.*;

/**
 * @author Natalia Balan
 */

@Entity
@Table(name = "cinemas")
public class Cinema {

    @Id
    @SequenceGenerator(name = "cinemasSeq", sequenceName = "cinemas_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cinemassSeq")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "cinema_name")
    private String cinemaName;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address cinemaAddress;

    @Column(name = "cinema_name")
    private String cinemaPhone;

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

}
