package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import repo.TestRepo;
import repo.TestRepoWithBeanImpl;
import service.TestService;
import service.TestServiceWithBeanImpl;

/**
 * Конфигурация приложения с указанием использованных бинов.
 * <p>
 * Сделала 2 варианта:
 * 1) Долгий (прописываем в теле класса) - полезен для 3rd party projects
 * 2) Быстрый (через сканирование компонент)
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
@Configuration
@ComponentScan(basePackageClasses = {TestRepoWithBeanImpl.class, TestServiceWithBeanImpl.class})
public class TestConfig {

    //@Bean
    public TestRepo getTestRepo() {
        return new TestRepoWithBeanImpl();
    }


    //@Bean
    public TestService getTestService() {
        return new TestServiceWithBeanImpl(getTestRepo());
    }


}
