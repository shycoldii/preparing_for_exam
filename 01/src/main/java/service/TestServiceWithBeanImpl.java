package service;

import org.springframework.stereotype.Service;
import repo.TestRepo;

/**
 * Реализация сервиса, используя аннотации и конфигурацию
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
@Service
public class TestServiceWithBeanImpl implements TestService {

    private TestRepo testRepo;

    public TestServiceWithBeanImpl(TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    @Override
    public TestRepo getRepo() {
        return testRepo;
    }
}
