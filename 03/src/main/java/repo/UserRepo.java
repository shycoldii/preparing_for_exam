package repo;

import entity.User;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * query - вернет лист
 * queryForObject - один объект
 * queryForList - можно вернуть List<Map<String,Object> ну или если один столбец, то его тип-  будет List<тип>
 * queryForMap  - знаем, что будет одна строка
 *
 * @author Darya Alexandrova
 * @since 2022.09.18
 */
public interface UserRepo {

    public List<User> findById(String id);
    public List<User> findAll();
    public List<Map<String,Object>> findByAge(int age);
    public Map<String,Object> findByAgeWithMap(int age);
    public ResultSetWrappingSqlRowSet findByAgeWithRowSet(int age);
    public Float getAverageAgeWithHandler();
    public Float getAverageAgeWithoutHandler();
    public Optional<User> findByIdWithObject(String id);
    public List<User> findByAgeWithBeanProperty(int age);

    public Optional<User> findByIdWithDeclTransactional(String id);
    public Optional<User> findByIdWithImpTransactional(String id);
    public void findByIdFromAnotherMethod(String id);
    public void findByIdWithException(String id);
    public void findByIdWithExceptionNoRollback(String id);
    public void findByIdWithTimeout(String id) throws InterruptedException;
    public void readOnlyWithoutReadOnly();

    @Transactional
    public void checkTransactional();
}
