import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pure_jpa.config.JpaConfig;

/**
 * Приложение для запуска JPA.
 *
 * @author Darya Alexandrova
 * @since 2022.10.28
 */
public class JpaApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        context.registerShutdownHook();
        System.out.println("Context is ready");
    }
}
