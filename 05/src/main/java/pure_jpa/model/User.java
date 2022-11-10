package pure_jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.09.18
 */
@Entity
@NamedQueries({
        @NamedQuery(name = pure_jpa.model.User.FIND_BY_NAME,
                query = "from User u where u.name=:name")})
public class User {

    /**
     * Без него не заработает
     */
    public User() {
    }

    public User(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static final String FIND_BY_NAME = "findByName";

    @Id
    private String id;

    private String name;
    private Integer age;

    @OneToMany( mappedBy = "user")
    private List<Cat> cats = new ArrayList<>();

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
