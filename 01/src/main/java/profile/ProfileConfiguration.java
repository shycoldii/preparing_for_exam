package profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.12
 */
@Configuration
@Profile("dev")
@ComponentScan
public class ProfileConfiguration {


    //из-за этого не создастся @Profile("notdev")
    //так тоже при активном профайле дев @Profile("default")
    @Bean
    public BeanBd getBeanBd() {
        return new BeanBd();
    }
}
