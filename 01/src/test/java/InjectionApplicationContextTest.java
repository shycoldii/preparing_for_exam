import config.MyTestConfig;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repo.TestRepo;
import service.TestService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

/**
 * Тесты для проверки корректной работы контекста.
 *
 * @author Darya Alexandrova
 * @since 2022.07.31
 */
public class InjectionApplicationContextTest {

    @Test
    public void testOneBeanAliases() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(MyTestConfig.class);
context.registerShutdownHook();

        TestService beanOne = context.getBean("beanOne", TestService.class);
        TestService beanTwo = context.getBean("beanTwo", TestService.class);

        assertNotNull(beanOne);
        assertNotNull(beanTwo);
        assertEquals(beanOne, beanTwo);

        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            context.getBean("beanThree", TestService.class);
        });
       context.close();

    }
}
