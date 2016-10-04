package md.usarb.cinema.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Natalia Balan
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @SequenceGenerator(name = "categoriesSeq", sequenceName = "categories_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoriesSeq")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category_id")
    private Set<CinemaCategory> cinemaCategories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<CinemaCategory> getCinemaCategories() {
        return cinemaCategories;
    }

    public void setCinemaCategories(Set<CinemaCategory> cinemaCategories) {
        this.cinemaCategories = cinemaCategories;
    }
}
