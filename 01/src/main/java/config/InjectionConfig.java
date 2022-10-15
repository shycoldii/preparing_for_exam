package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.InjectionServiceImpl;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.07.30
 */
@Configuration
@ComponentScan(basePackageClasses = {InjectionServiceImpl.class})
public class InjectionConfig {

}
