package scope;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * By default, Spring uses CGLIB library to directly subclass the objects.
 * To avoid CGLIB usage, we can configure the proxy mode with ScopedProxyMode.INTERFACES,
 * to use the JDK dynamic proxy instead.
 *
 * @author Darya Alexandrova
 * @since 2022.08.03
 */
@Component
@Scope(value = "prototype",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PrototypeProxyBean {
}
