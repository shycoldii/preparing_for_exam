package scope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.09
 */
@Component
@Scope(value = "prototype",proxyMode= ScopedProxyMode.TARGET_CLASS)
public class PrototypeNotInjectedBean {
}
