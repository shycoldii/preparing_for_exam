package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import repo.TestRepo;
import repo.TestRepoWithBeanImpl;
import service.TestService;
import service.TestServiceWithBeanImpl;

/**
 * Конфигурация для тестов.
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
@Configuration
public class MyTestConfig {

    @Bean
    public TestRepo getTestRepo() {
        return new TestRepoWithBeanImpl();
    }


    //причем второе наименование просто альяс (создается только beanOne)
    @Bean(name = {"beanOne","beanTwo"})
    public TestService getTestService() {
        return new TestServiceWithBeanImpl(getTestRepo());
    }


}