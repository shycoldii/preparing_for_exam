package pure_jpa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pure_jpa.model.Cat;
import pure_jpa.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Сервис, работающий со стандартным PersistenceContext
 *
 * @author Darya Alexandrova
 * @since 2022.10.29
 */
@Service
public class TransactionPersistenceContextUserService {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public User insertWithTransaction(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public Cat insertWithTransaction(Cat cat) {
        entityManager.persist(cat);
        return cat;
    }

    @Transactional
    public void removeCat(Cat cat){
        cat.getUser().getCats().remove(cat);

        entityManager.persist(cat.getUser());
        entityManager.remove(cat);

    }

    public User insertWithoutTransaction(User user) {
        entityManager.persist(user);
        return user;
    }

    public User find(String id) {
        return entityManager.find(User.class, id);
    }

    public Cat findCat(String id) {
        return entityManager.find(Cat.class, id);
    }

    public List<User> findByName(String name){
        return entityManager.createNamedQuery(User.FIND_BY_NAME, User.class).setParameter("name",name).getResultList();
    }


    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void refreshUser(User user){
        entityManager.refresh(user);
    }

    @Transactional
    public void flush(){
        entityManager.flush();
    }
}
