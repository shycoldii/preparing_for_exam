import config.TestJpaConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pure_jpa.model.Cat;
import pure_jpa.model.User;
import pure_jpa.repo.UserDefinitionRepository;
import pure_jpa.repo.UserRepository;
import pure_jpa.service.ExtendedPersistenceContextUserService;
import pure_jpa.service.TransactionPersistenceContextUserService;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.10.29
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestJpaConfig.class)
public class UserPersistenceServiceTest {

    @Autowired
    private TransactionPersistenceContextUserService transactionPersistenceContextUserService;

    @Autowired
    private ExtendedPersistenceContextUserService extendedPersistenceContextUserService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDefinitionRepository userDefinitionRepository;

    @Test
    public void testTransactionPersistenceService() {

        User user = new User("1", "Devender", 1);
        transactionPersistenceContextUserService.insertWithTransaction(user);

        User userFromTransactionPersistenceContext = transactionPersistenceContextUserService
                .find(user.getId());
        assertNotNull(userFromTransactionPersistenceContext);

        //видны изменения с transactionPersistenceContext
        User userFromExtendedPersistenceContext = extendedPersistenceContextUserService
                .find(user.getId());
        assertNotNull(userFromExtendedPersistenceContext);

    }

    @Test(expected = TransactionRequiredException.class)
    public void testThatUserSaveWithoutTransactionThrowException() {
        User user = new User("122L", "Devender", 11);
        transactionPersistenceContextUserService.insertWithoutTransaction(user);
    }

    @Test
    //сохранит в кеш
    public void testExtendedPersistenceServiceWithoutTransaction() {

        User user = new User("1", "Devender", 1);
        extendedPersistenceContextUserService.insertWithoutTransaction(user);

        //видны изменения с кеша
        User userFromExtendedPersistenceContext = extendedPersistenceContextUserService
                .find(user.getId());
        assertNotNull(userFromExtendedPersistenceContext);

        //не видны изменения
        User userFromTransactionPersistenceContext = transactionPersistenceContextUserService
                .find(user.getId());
        assertNull(userFromTransactionPersistenceContext);

    }

    @Test
    //сохранит в постоянное хранилище
    public void testExtendedPersistenceServiceWitTransaction() {

        User user = new User("1", "Devender", 1);
        extendedPersistenceContextUserService.insertWithTransaction(user);

        //видны изменения с кеша
        User userFromExtendedPersistenceContext = extendedPersistenceContextUserService
                .find(user.getId());
        assertNotNull(userFromExtendedPersistenceContext);

        //видны изменения
        User userFromTransactionPersistenceContext = transactionPersistenceContextUserService
                .find(user.getId());
        assertNotNull(userFromTransactionPersistenceContext);

    }

    @Test(expected = EntityExistsException.class)
    //внутри кеша нельзя с одинаковыми ИДС сохранять
    public void testThatPersistUserWithSameIdentifierThrowException() {
        User user1 = new User("126L", "Devender", 1);
        User user2 = new User("126L", "Devender", 1);
        extendedPersistenceContextUserService.insertWithoutTransaction(user1);
        extendedPersistenceContextUserService.insertWithoutTransaction(user2);
    }

    @Test
    //сработает тригер на сохранение в бд только при withTransaction
    public void testExtendedPersistenceServiceFlushing() {

        User user = new User("1", "Devender", 1);
        extendedPersistenceContextUserService.insertWithoutTransaction(user);

        //видны изменения с кеша
        User userFromExtendedPersistenceContext = extendedPersistenceContextUserService
                .find(user.getId());
        assertNotNull(userFromExtendedPersistenceContext);

        //не видны изменения
        User userFromTransactionPersistenceContext = transactionPersistenceContextUserService
                .find(user.getId());
        assertNull(userFromTransactionPersistenceContext);

        User user2 = new User("2", "Devender2", 2);
        extendedPersistenceContextUserService.insertWithTransaction(user2);


        //видны изменения
        User userFromTransactionPersistenceContext2 = transactionPersistenceContextUserService
                .find(user.getId());
        assertNotNull(userFromTransactionPersistenceContext2);
    }

    @Test(expected = DataIntegrityViolationException.class)
    //есть желание проверить, как сохранит одинаковые сущности
    public void testTransactionPersistenceServiceDubl() {

        User user1 = new User("1", "Devender", 1);
        transactionPersistenceContextUserService.insertWithTransaction(user1);

        //User user2 = new User("1", "Devender", 1); --вызвало бы ошибку

        transactionPersistenceContextUserService.insertWithTransaction(user1);

    }

    @Test
    //есть желание проверить, как сохранит одинаковые сущности
    public void testExtendedPersistenceServiceDubl() {

        User user1 = new User("1", "Devender", 1);
        extendedPersistenceContextUserService.insertWithTransaction(user1);
        extendedPersistenceContextUserService.insertWithTransaction(user1);

    }

    @Test
    public void testfindUserByName() {

        User user1 = new User("1", "Devender", 1);
        transactionPersistenceContextUserService.insertWithTransaction(user1);
        User user2 = new User("2", "Devender", 1);
        transactionPersistenceContextUserService.insertWithTransaction(user2);

        assertEquals(transactionPersistenceContextUserService.findByName("Devender").size(), 2);

    }

    @Test
    public void testFlush() {

        User user = new User("1", "Devender", 1);
        extendedPersistenceContextUserService.insertWithoutTransaction(user);

        //не видны изменения
        User userFromTransactionPersistenceContext = transactionPersistenceContextUserService
                .find(user.getId());
        assertNull(userFromTransactionPersistenceContext);

        extendedPersistenceContextUserService.flush();

        //видны изменения
        User userFromTransactionPersistenceContext2 = transactionPersistenceContextUserService
                .find(user.getId());
        assertNotNull(userFromTransactionPersistenceContext2);
    }

    @Test
    public void testMerge() {

        User user = new User("1", "Devender", 1);
        transactionPersistenceContextUserService.updateUser(user);

        User userFromTransactionPersistenceContext = transactionPersistenceContextUserService
                .find(user.getId());
        assertNotNull(userFromTransactionPersistenceContext);
    }

    @Test
    @Transactional
    public void testRefresh() {

        User user = new User("1", "Devender", 1);
        transactionPersistenceContextUserService.insertWithTransaction(user);
        //иначе на момент рефреша еще не будет закомичен юзер в бд
        transactionPersistenceContextUserService.flush();

        transactionPersistenceContextUserService.refreshUser(user);
        user.setName("Dev");
        transactionPersistenceContextUserService.updateUser(user);
        transactionPersistenceContextUserService.flush();

        User userFromTransactionPersistenceContext = transactionPersistenceContextUserService
                .find(user.getId());
        assertEquals(userFromTransactionPersistenceContext.getName(), "Dev");

        user.setName("dfghjk");
        //заберем Dev
        transactionPersistenceContextUserService.refreshUser(user);

        userFromTransactionPersistenceContext = transactionPersistenceContextUserService
                .find(user.getId());

        assertEquals(userFromTransactionPersistenceContext.getName(), "Dev");
    }

    @Test
    @Transactional //for lazy
    public void testDeleteCat() {
        //ожидаем: удаляем кота => удаляется из User

        User user = new User("1", "Devender", 1);
        transactionPersistenceContextUserService.insertWithTransaction(user);
        Cat cat = new Cat("1", user);
        user.getCats().add(cat);

        User userFromTransactionPersistenceContext = transactionPersistenceContextUserService
                .find(user.getId());
        assertEquals(1, userFromTransactionPersistenceContext.getCats().size());

        transactionPersistenceContextUserService.removeCat(cat);

        userFromTransactionPersistenceContext = transactionPersistenceContextUserService
                .find(user.getId());
        assertNull(transactionPersistenceContextUserService
                .findCat(cat.getId()));
        assertEquals(0, userFromTransactionPersistenceContext.getCats().size());
    }

    @Test
    public void testSpringDataRepository() {
        for (int i = 0; i < 100; i += 1) {
            User user = new User(String.valueOf(i), "Devender", 100);
            userRepository.save(user);
        }

        Page<User> pageableUsers = userRepository.findAll(PageRequest.of(0, 10));
        assertEquals(10, pageableUsers.getSize());

        assertNotNull(userRepository.findUserById("1"));
        assertNotNull(userDefinitionRepository.findUserById("1"));
    }

}
