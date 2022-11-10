package pure_jpa.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pure_jpa.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Сервис, работающий с extended PersistenceContext
 * фичи: можем persist без транзакции, но flush только с транзакцией
 *
 *
 * @author Darya Alexandrova
 * @since 2022.10.29
 */
@Service
public class ExtendedPersistenceContextUserService {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Transactional
    public User insertWithTransaction(User user) {
        entityManager.persist(user);
        return user;
    }

    public User insertWithoutTransaction(User user) {
        entityManager.persist(user);
        return user;
    }

    public User find(String id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void flush(){
        entityManager.flush();
    }
}
