package pure_jpa.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PreRemove;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.10.29
 */
@Entity
@NamedQuery(name = Cat.FIND_BY_USER, query = "from Cat c where c.user=:user_id")
public class Cat {

    public static final String FIND_BY_USER = "findByUserId";
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Cat() {
    }

    public Cat(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
