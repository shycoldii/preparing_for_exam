package hibernate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.10.29
 */
@Entity
public class Table {

    @Id
    private String id;

    @OneToMany(mappedBy = "table")
    List<Plate> plates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    public Table(String id) {
        this.id = id;
    }
    public Table(){

    }
}
