package autowired.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.07
 */
@Configuration
@ComponentScan(basePackages = "autowired.service")
public class AutowiredConfiguration {
}
