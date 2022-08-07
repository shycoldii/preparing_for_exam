package lifecycle.repo;

import org.springframework.stereotype.Repository;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.06
 */
@Repository
public class MyLifecycleRepo implements LifecycleRepo {

    public MyLifecycleRepo() {
        System.out.println("MyLifecycleRepoConstructor");
    }
}
