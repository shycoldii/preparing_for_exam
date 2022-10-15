import config.JdbcConfiguration;
import org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import repo.UserRepo;
import repo.UserRepoImpl;

import java.sql.SQLException;

/**
 * Основное приложение для понимания JdbcTemplate и транзакций.
 * check ProxyTransactionManagementConfiguration for debugging
 *
 * @author Darya Alexandrova
 * @since 2022.09.13
 */
public class JDBCApplication {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        context.getBean(UserRepo.class).findById("1").forEach(System.out::println);
        context.getBean(UserRepo.class).findByIdWithObject("1").ifPresent(System.out::println);
        context.getBean(UserRepo.class).findAll().forEach(System.out::println);
        System.out.println(context.getBean(UserRepo.class).getAverageAgeWithHandler());
        System.out.println(context.getBean(UserRepo.class).getAverageAgeWithoutHandler());
        context.getBean(UserRepo.class).findByAge(20).forEach(System.out::println);
        System.out.println(context.getBean(UserRepo.class).findByAgeWithMap(21));
        //можно итерировать
        System.out.println(context.getBean(UserRepo.class).findByAgeWithRowSet(20).getResultSet().getRow());
        //штука сама конвертирует при явном соответствии User модели и колонок
        context.getBean(UserRepo.class).findByAgeWithBeanProperty(20).forEach(System.out::println);

        //транзакции (для императивного метода не нужна @enable активация)
        context.getBean(UserRepo.class).findByIdWithImpTransactional("1").ifPresent(System.out::println);

        //если не ставить @enable, то ничего не упадет - просто не будет логики транзакции
        context.getBean(UserRepo.class).findByIdWithDeclTransactional("1").ifPresent(System.out::println);

        try{
            context.getBean(UserRepo.class).findByIdWithTimeout("1");
        }
        catch (InterruptedException in){
            in.getStackTrace();
        }

        context.getBean(UserRepo.class).readOnlyWithoutReadOnly();

        try{
            context.getBean(UserRepo.class).findByIdWithException("1");
        }
        catch (DuplicateKeyException d){
            //откат совершен - 333 не добавился даже первый раз
            context.getBean(UserRepo.class).findAll().forEach(System.out::println);
            System.out.println("EXCEPTION END");
        }

        context.getBean(UserRepo.class).findByIdFromAnotherMethod("1");

        try{
            context.getBean(UserRepo.class).findByIdWithExceptionNoRollback("1");
        }
        catch (DuplicateKeyException d){
            System.out.println("EXCEPTION NO ROLLBACK END");
            context.getBean(UserRepo.class).findAll().forEach(System.out::println);
        }


        try{
            context.getBean(UserRepo.class).checkTransactional();
        }
        catch (DuplicateKeyException d){
            //откат совершен - 888 не добавился даже первый раз
            context.getBean(UserRepo.class).findAll().forEach(System.out::println);
        }

    }
}
