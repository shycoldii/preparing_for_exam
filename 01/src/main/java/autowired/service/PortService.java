package autowired.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.07
 */
@Service
public class PortService {

    @Autowired AutowiredService rrr;

    /**
     * Доступно два бина для инжекта - MyAutowired/ AnotherAutowired
     *
     * @param autowiredService
     */
    PortService(@Qualifier("mm")
                AutowiredService autowiredService){
        System.out.println(autowiredService);
    }

    public AutowiredService getMyAutowiredService() {
        return rrr;
    }
}
