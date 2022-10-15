package lazy.config;

import lazy.service.LazyServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.09
 */
@Configuration
@ComponentScan(basePackageClasses = LazyServiceImpl.class, lazyInit = true)
public class LazyConfiguration {

}
