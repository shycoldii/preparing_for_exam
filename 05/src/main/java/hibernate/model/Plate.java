package hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.10.29
 */
@Entity
public class Plate {

    @Id
    private String id;

    @ManyToOne
    private Table table;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Plate(String id, Table table) {
        this.id = id;
        this.table = table;
    }

    public Plate(){
    }
}
