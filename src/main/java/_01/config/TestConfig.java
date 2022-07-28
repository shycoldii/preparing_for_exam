package _01.config;

import _01.repo.TestRepo;
import _01.repo.TestRepoImpl;
import _01.repo.TestRepoWithBeanImpl;
import _01.service.TestService;
import _01.service.TestServiceImpl;
import _01.service.TestServiceWithBeanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
@Configuration
@ComponentScan(basePackageClasses = {TestRepoWithBeanImpl.class,TestServiceWithBeanImpl.class})
public class TestConfig {

    //@Bean
    public TestRepo getTestRepo(){
        return new TestRepoWithBeanImpl();
    };

    //@Bean
    public TestService getTestService(){
        return new TestServiceWithBeanImpl(getTestRepo());
    };

}
