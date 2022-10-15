package scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.08
 */
@Component
@Scope(scopeName = "prototype")
public class MyBean {
}
