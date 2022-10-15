package autowired.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.07
 */
@Component("rrr")
//@Primary
public class AnotherAutowiredService implements AutowiredService {

    public AnotherAutowiredService() {
        System.out.println("Another!");
    }
}
