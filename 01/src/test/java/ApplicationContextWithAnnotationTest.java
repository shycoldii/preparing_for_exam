import config.MyTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import repo.TestRepo;
import repo.TestRepoImpl;
import repo.TestRepoWithBeanImpl;
import service.TestService;
import service.TestServiceImpl;
import service.TestServiceWithBeanImpl;

import static org.junit.Assert.assertNotNull;

/**
 * Тест для проверки поднятия контекста через аннотации.
 *
 * Второй вариант: использовать runWith+ComponentScan (будут просматриваться пакеты в тестах)
 * (лучше так не делать, так как понадобится еще enableAutoConfiguration SpringBoot)
 *
 * @author Darya Alexandrova
 * @since 2022.07.31
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= MyTestConfig.class)
//@ComponentScan(basePackageClasses = {TestRepoWithBeanImpl.class, TestServiceWithBeanImpl.class})
public class ApplicationContextWithAnnotationTest {

    @Autowired
    TestRepo testRepo;

    @Autowired
    TestService testService;

    @Test
    public void getTestRepoBean(){
        assertNotNull(testRepo);
        assertNotNull(testService);
    }
}
