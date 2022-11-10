package pure_jpa.repo;

import org.springframework.data.repository.RepositoryDefinition;
import pure_jpa.model.User;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.10.29
 */
@RepositoryDefinition(domainClass = User.class,idClass = String.class)
public interface UserDefinitionRepository {
    public User findUserById(String id);
}
