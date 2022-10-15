package autowired.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.07
 */
@Component("mm")
//@Primary
//@Qualifier("mm")
public class MyAutowiredService implements AutowiredService {

    public MyAutowiredService(){
        System.out.println("My!");
    }
}
