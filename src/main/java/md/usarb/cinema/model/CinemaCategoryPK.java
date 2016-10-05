package md.usarb.cinema.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;

/**
 * @author Balan Natalia
 */
@Embeddable
public class CinemaCategoryPK implements Serializable {

    @ManyToOne
    @PrimaryKeyJoinColumn
    protected Cinema cinema;

    @ManyToOne
    @PrimaryKeyJoinColumn
    protected Category category;

    public CinemaCategoryPK() {
    }

    public CinemaCategoryPK(Cinema cinema, Category category) {
        this.cinema = cinema;
        this.category = category;
    }
}
