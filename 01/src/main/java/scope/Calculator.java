package scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.08
 */
@Component
public class Calculator {
    private MyBean myBean;

    public int sum(int a, int b) {
        return a + b;
    }

    @Bean
    public MyBean myBean() {
        this.myBean = new MyBean();
        return myBean;
    }
}
