package md.usarb.cinema.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Natalia Balan
 */
@Entity
@Table(name = "cinema_categories")
public class CinemaCategory implements Serializable {

    @EmbeddedId
    private CinemaCategoryPK cinemaCategoryPK;

    @Column(name = "capacity")
    private int capacity;

    public CinemaCategoryPK getCinemaCategoryPK() {
        return cinemaCategoryPK;
    }

    public void setCinemaCategoryPK(CinemaCategoryPK cinemaCategoryPK) {
        this.cinemaCategoryPK = cinemaCategoryPK;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
