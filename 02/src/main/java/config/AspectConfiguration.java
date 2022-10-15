package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.17
 */
@Configuration
@ComponentScan(basePackages = {"service","aspect"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfiguration {
}
