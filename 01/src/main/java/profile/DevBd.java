package profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.12
 */
@Component
@Profile("dev")
public class DevBd {
}
