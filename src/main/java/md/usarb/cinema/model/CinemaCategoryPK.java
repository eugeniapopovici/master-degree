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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CinemaCategoryPK)) return false;

        CinemaCategoryPK that = (CinemaCategoryPK) o;

        if (!cinema.equals(that.cinema)) return false;
        return category.equals(that.category);

    }

    @Override
    public int hashCode() {
        int result = cinema.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }
}
