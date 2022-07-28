package _01.service;

import _01.repo.TestRepo;
import org.springframework.stereotype.Service;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
@Service
public class TestServiceWithBeanImpl implements TestService {
    private TestRepo testRepo;

    public TestServiceWithBeanImpl(TestRepo testRepo){
        this.testRepo = testRepo;
    }

    @Override
    public TestRepo getRepo() {
        return testRepo;
    }
}
