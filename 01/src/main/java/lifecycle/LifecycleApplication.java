package lifecycle;

import lifecycle.config.LifecycleConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Демонстрация жизненного цикла Спринга.
 * <p>
 * Еще есть возможность третьего конструктора - через eventListener на refresh приложения, например.
 * В тот момент уже все бины инициализированы и настроены BPP (после BPP2)
 *
 * @author Darya Alexandrova
 * @since 2022.08.06
 */
public class LifecycleApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifecycleConfig.class);
        context.registerShutdownHook();
    }
}
