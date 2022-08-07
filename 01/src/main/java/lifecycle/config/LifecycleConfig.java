package lifecycle.config;

import lifecycle.repo.MyLifecycleRepo;
import lifecycle.repo.TheirLifecycleRepo;
import lifecycle.service.MyLifecycleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.06
 */
@Configuration
public class LifecycleConfig {

    @Bean
    public MyLifecycleRepo lifecycleRepo() {
        return new MyLifecycleRepo();
    }

    @Bean
    public TheirLifecycleRepo theirLifecycleRepo() {
        return new TheirLifecycleRepo();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public MyLifecycleService lifecycleService() {
        return new MyLifecycleService(lifecycleRepo());
    }

}
