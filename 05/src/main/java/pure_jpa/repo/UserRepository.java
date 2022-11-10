package pure_jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pure_jpa.model.User;

/**
 * Интерфейс Spring Data для личных запросов.
 *
 * @author Darya Alexandrova
 * @since 2022.10.29
 */
public interface UserRepository extends JpaRepository<User,String> {

    public User findUserById(String id);
}
