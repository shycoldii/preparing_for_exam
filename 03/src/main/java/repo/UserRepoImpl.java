package repo;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Имплементация репозитория по работе с пользователями.
 * Тут всякие интересные примеры по работе с JDBC.
 *
 * @author Darya Alexandrova
 * @since 2022.09.18
 */
@Repository
public class UserRepoImpl implements UserRepo {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    private final RowMapper<User> rowMapper = (rs, rowNum) -> {
        var id = rs.getString("id");
        var name = rs.getString("name");
        var age = rs.getInt("age");
        var user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    };

    UserRepoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Optional<User> findByIdWithObject(String id) {
        //var sql = "select ID, NAME, AGE from USER where ID= ?";
        //это вариант для NamedJdbcTemplate
        var sql = "select ID, NAME, AGE from USER where ID=:id";
        return Optional.of(namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", "1"), rowMapper));
        //return Optional.of(jdbcTemplate.queryForObject(sql,
        //        rowMapper, id));
    }

    @Override
    public List<User> findById(String id) {
        var sql = "select ID, NAME, AGE from USER where ID=:id";
        return namedParameterJdbcTemplate.query(sql, Map.of("id", "1"), rowMapper);
    }

    @Override
    public Map<String, Object> findByAgeWithMap(int age) {
        var sql = "select ID, NAME, AGE from USER where AGE=:age";
        return namedParameterJdbcTemplate.queryForMap(sql, Map.of("age", age));
    }

    @Override
    public ResultSetWrappingSqlRowSet findByAgeWithRowSet(int age) {
        var sql = "select ID, NAME, AGE from USER where AGE=:age";
        return (ResultSetWrappingSqlRowSet) namedParameterJdbcTemplate.queryForRowSet(sql, Map.of("age", age));
    }

    @Override
    public List<User> findByAgeWithBeanProperty(int age) {
        var sql = "select ID, NAME, AGE from USER where age=:age";
        return namedParameterJdbcTemplate.query(sql, Map.of("age", age), new BeanPropertyRowMapper(User.class));
    }

    @Override
    public List<Map<String, Object>> findByAge(int age) {
        var sql = "select ID, NAME, AGE from USER where AGE=:age";
        return namedParameterJdbcTemplate.queryForList(sql, Map.of("age", age));
    }

    @Override
    public List<User> findAll() {
        var sql = "select * FROM USER";
        return jdbcTemplate.query(sql,
                rowMapper);
    }

    @Override
    public Float getAverageAgeWithHandler() {
        UserCallBackHandler userCallBackHandler = new UserCallBackHandler();
        var sql = "select * FROM USER";
        jdbcTemplate.query(sql,
                userCallBackHandler);
        return userCallBackHandler.getAverageAge();
    }

    @Override
    public Float getAverageAgeWithoutHandler() {
        var sql = "select cast(avg(age) as decimal(10,2)) FROM USER";
        return jdbcTemplate.queryForObject(sql, Float.class);
    }

    @Override
    public Optional<User> findByIdWithImpTransactional(String id) {
        return transactionTemplate.execute(status -> {
            Optional<User> opt = Optional.empty();
            try {
                var sql = "select ID, NAME, AGE from USER where ID=:id";
                opt = Optional.of(namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", id), rowMapper));
            } catch (Exception e) {
                status.setRollbackOnly();
            }
            return opt;
        });
    }

    @Override
    @Transactional
    public Optional<User> findByIdWithDeclTransactional(String id) {
        var sql = "select ID, NAME, AGE from USER where ID=:id";
        return Optional.of(namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", "1"), rowMapper));
    }

    @Override
    public void findByIdFromAnotherMethod(String id) {
        //мы не транзакционные, но хотим транзакцию в findByIdWithDecl - ее не будет
        findByIdWithDeclTransactional("1");

    }

    @Override
    @Transactional()
    public void findByIdWithException(String id) {
        System.out.println("EXCEPTION");
        String sql = "INSERT INTO USER (ID,NAME,AGE)\n" +
                "VALUES (:id,:name,:age);";
        namedParameterJdbcTemplate.update(sql, Map.of("id", "333", "name", "test", "age", "333"));
        //unique id нарушение - мы должны откатиться
        namedParameterJdbcTemplate.update(sql, Map.of("id", "333", "name", "test", "age", "333"));
    }

    @Override
    @Transactional(noRollbackFor = DuplicateKeyException.class)
    public void findByIdWithExceptionNoRollback(String id) {
        //не откатились по первому изменению
        System.out.println("EXCEPTION NO ROLLBACK");
        String sql = "INSERT INTO USER (ID,NAME,AGE)\n" +
                "VALUES (:id,:name,:age);";
        namedParameterJdbcTemplate.update(sql, Map.of("id", "333", "name", "test", "age", "333"));
        //unique id нарушение - мы должны откатиться
        namedParameterJdbcTemplate.update(sql, Map.of("id", "333", "name", "test", "age", "333"));
    }

    public void checkTransactional() {
        //если в интерфейсе аннотация, то србаотает даже при cglib!!
        String sql = "INSERT INTO USER (ID,NAME,AGE)\n" +
                "VALUES (:id,:name,:age);";
        namedParameterJdbcTemplate.update(sql, Map.of("id", "888", "name", "test", "age", "333"));
        //unique id нарушение - мы должны откатиться
        namedParameterJdbcTemplate.update(sql, Map.of("id", "888", "name", "test", "age", "333"));
    }

    @Override
    //это миллисекунды
    @Transactional(timeout = 1)
    public void findByIdWithTimeout(String id) throws InterruptedException {

        Thread.sleep(10);
        System.out.println("время вышло?");
        //если бы не было sql кода, ничего бы не упало (а так выпадает TransactionTimedOutException - runtime и делается rollback)
        var sql = "select ID, NAME, AGE from USER where ID=:id";
        Optional.of(namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", "1"), rowMapper));
    }

    @Override
    @Transactional(readOnly = true)
    public void readOnlyWithoutReadOnly() {
        //мы не падаем! это всего лишь подсказка, но она может быть проигнорирована (для оптимизации сделано)
        var sql = "select ID, NAME, AGE from USER where ID=:id";
        Optional.of(namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", "1"), rowMapper));

        System.out.println("все супер! я умею читать!");
        sql = "INSERT INTO USER (ID,NAME,AGE)\n" +
                "VALUES (:id,:name,:age);";
        namedParameterJdbcTemplate.update(sql, Map.of("id", "999", "name", "test", "age", "999"));
        System.out.println("я умею писать с рид онли?");
        sql = "select ID, NAME, AGE from USER";
        System.out.println(namedParameterJdbcTemplate.query(sql, rowMapper));

    }
}

/**
 * Хендлер. Показывает смысл его работы
 * Но вообще штука удобна для конвертации в html/xml, сохранить, залогировать
 */
class UserCallBackHandler implements RowCallbackHandler {
    private float countAge;
    private int count;

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        countAge += rs.getInt("age");
        count++;
    }

    public float getAverageAge() {
        return countAge / count;
    }
}

class UserResultExtractor implements ResultSetExtractor<Float> {

    @Override
    public Float extractData(ResultSet rs) throws SQLException, DataAccessException {
        //тут логика преобразования всего rs(нужно while rs.next()) во что-то
        return null;
    }
}