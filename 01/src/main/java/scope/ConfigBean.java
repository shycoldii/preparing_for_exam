package scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.03
 */
@Configuration
@ComponentScan("scope")
public class ConfigBean {

    @Bean
    public SingletonBean singletonBean(){
        return new SingletonBean(prototypeBean());
    }

    @Bean
    @Scope("prototype")
    public PrototypeBean prototypeBean(){
        return new PrototypeBean();
    }
}
