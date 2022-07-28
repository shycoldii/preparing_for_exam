package _01.repo;

import org.springframework.stereotype.Repository;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
@Repository
public class TestRepoWithBeanImpl implements TestRepo {

    @Override
    public String getTestId() {
        return "2";
    }
}
