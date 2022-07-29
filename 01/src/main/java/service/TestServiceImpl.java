package service;


import repo.TestRepo;


/**
 * Базовая реализация сервиса через XML. Старый подход.
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
public class TestServiceImpl implements TestService {

    private TestRepo testRepo;

    public TestServiceImpl(TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    @Override
    public TestRepo getRepo() {
        return testRepo;
    }
}
