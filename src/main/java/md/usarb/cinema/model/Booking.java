package md.usarb.cinema.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Natalia Balan
 */
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @SequenceGenerator(name = "bookingsSeq", sequenceName = "bookings_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingsSeq")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Customer customer;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Showing showing;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Category category;

    @Column(name = "booking_for_date")
    private LocalDate bookingForDate;

    @Column(name = "booking_made_date")
    private LocalDate bookingMadeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getBookingForDate() {
        return bookingForDate;
    }

    public void setBookingForDate(LocalDate bookingForDate) {
        this.bookingForDate = bookingForDate;
    }

    public LocalDate getBookingMadeDate() {
        return bookingMadeDate;
    }

    public void setBookingMadeDate(LocalDate bookingMadeDate) {
        this.bookingMadeDate = bookingMadeDate;
    }
}
